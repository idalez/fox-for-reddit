package io.github.gusandrianos.foxforreddit.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import io.github.gusandrianos.foxforreddit.data.models.Token
import io.github.gusandrianos.foxforreddit.data.models.gencomments.Comments
import io.github.gusandrianos.foxforreddit.data.models.gene.SinglePost
import io.github.gusandrianos.foxforreddit.data.models.genpost.Post
import io.github.gusandrianos.foxforreddit.data.network.RedditAPI
import io.github.gusandrianos.foxforreddit.data.network.RetrofitService
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object PostRepository {
    private val redditAPI: RedditAPI = RetrofitService.getRedditAPIInstance()
    private val dataSinglePost = MutableLiveData<SinglePost>()



    fun getPosts(subreddit: String, filter: String, token: Token) =
            Pager(
                    config = PagingConfig(pageSize = 25, prefetchDistance = 100, enablePlaceholders = false),
                    pagingSourceFactory = { PostPagingSource(subreddit, filter, token) }
            ).liveData

    fun votePost(dir: String, id: String, token: Token) {
        val bearer = " " + token.tokenType + " " + token.accessToken
        var vote = redditAPI.votePost(bearer, dir, id, 123)
        vote.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                //ToDo votePost onResponse
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                //ToDo votePost onFailure
            }
        })
    }

    fun getPostComments(subreddit: String, comments: String, article: String, token: Token):LiveData<SinglePost>{
        val bearer = " " + token.tokenType + " " + token.accessToken
        var comments = redditAPI.getPostComments(subreddit, comments, article, bearer)

        comments.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                val jsonArray = JSONArray(response.body().toString())
                Log.i("JSON", "onResponse: ${jsonArray[0].toString()}")
                Log.i("JSON", "onResponse: ${jsonArray[1].toString()}")

                //val post: Post = Gson().fromJson(jsonArray.getJSONObject(0).toString(), Post::class.java)


                val comments: Comments = Gson().fromJson(jsonArray.getJSONObject(1).toString(), Comments::class.java)

                val singlePost = SinglePost(comments)
//                Log.i("JSON", "onResponse: " + singlePost.post.kind)
                Log.i("JSON", "onResponse: " + singlePost.comments.kind)
                Log.i("JSON", singlePost.comments.data.children.toString())
                dataSinglePost.value = singlePost

            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.i("JSON", "onFailure: " + t.message)
            }

        })
        return dataSinglePost
    }
}