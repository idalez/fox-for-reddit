package io.github.gusandrianos.foxforreddit.data.repositories

import androidx.paging.PagingSource
import io.github.gusandrianos.foxforreddit.data.models.Data
import io.github.gusandrianos.foxforreddit.data.models.Listing
import io.github.gusandrianos.foxforreddit.data.network.RedditAPI
import io.github.gusandrianos.foxforreddit.data.network.RetrofitService
import retrofit2.HttpException
import java.io.IOException


class PostPagingSource() : PagingSource<String, Data>() {
    private val STARTER_PAGE = ""
    private val MODE_POST = 1
    private val MODE_SUBREDDIT = 2

    private val redditAPI: RedditAPI = RetrofitService.getRedditAPIInstance()
    private lateinit var mSubreddit: String
    private lateinit var mFilter: String
    private lateinit var mBearer: String
    private lateinit var mWhere: String
    private var MODE: Int = 0


    constructor (subreddit: String, filter: String, bearer: String): this(){
        mSubreddit = subreddit
        mFilter = filter
        mBearer = bearer
        MODE = MODE_POST
    }

    constructor (where: String, bearer: String): this() {
        mBearer = bearer
        mWhere = where
        MODE = MODE_SUBREDDIT
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Data> {
        val position = params.key ?: STARTER_PAGE

        return try {
            lateinit var response: Listing
            if (MODE == MODE_POST)
                response = redditAPI.getPostList(mSubreddit, mFilter, position, params.loadSize, mBearer)
            else if (MODE == MODE_SUBREDDIT)
                response = redditAPI.getSubreddits(mBearer, mWhere, position, params.loadSize)
            val items = response.data.children?.map { it.data } ?: emptyList()

            LoadResult.Page(
                    data = items,
                    prevKey = null,
                    nextKey = if (items.isEmpty()) null else response.data?.after
            )
        } catch (exception: IOException) {  //Maybe there is no Internet Connection when try to make request
            LoadResult.Error(exception)
        } catch (exception: HttpException) { //Maybe something went wrong on the server (ex. no data, or not authorized)
            LoadResult.Error(exception)
        }
    }
}