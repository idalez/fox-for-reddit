package io.github.gusandrianos.foxforreddit.data.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.github.gusandrianos.foxforreddit.data.models.Listing
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RedditAPI {
    /**
     * @param subreddit
     * Optional, references the subreddit to load posts from
     * @param filter
     * Possible values: Hot, New, Rising, Random.
     * @return
     * List of Posts
     */
    @GET("{subreddit}/{filter}")
    suspend fun getPostList(
            @Path("subreddit") subreddit: String,
            @Path("filter") filter: String,
            @Query("after") after: String,
            @Query("count") count: Int,
            @Header("Authorization") bearer: String
    ): Listing

    @GET("{subreddit}/{filter}")
    fun getPosts(
            @Path("subreddit") subreddit: String?,
            @Path("filter") filter: String?,
            @Query("after") after: String?,
            @Header("Authorization") bearer: String?
    ): Call<Listing>

    @FormUrlEncoded
    @POST("/api/vote")
    fun votePost(
            @Header("Authorization") bearer: String?,
            @Field("dir") dir: String?,
            @Field("id") id: String?,
            @Field("rank") rank: Int?
    ): Call<Void>

    @GET("{subreddit}/comments/{comments}/{article}")
    fun getPostComments(
            @Path("subreddit") subreddit: String,
            @Path("comments") comments: String,
            @Path("article") article: String,
            @Header("Authorization") bearer: String?
    ): Call<JsonArray>
}