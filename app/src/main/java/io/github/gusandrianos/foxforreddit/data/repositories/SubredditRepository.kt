package io.github.gusandrianos.foxforreddit.data.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.gusandrianos.foxforreddit.Constants.ACTION_SUBSCRIBE
import io.github.gusandrianos.foxforreddit.Constants.ACTION_UNSUBSCRIBE
import io.github.gusandrianos.foxforreddit.data.models.*
import io.github.gusandrianos.foxforreddit.data.network.RedditAPI
import io.github.gusandrianos.foxforreddit.data.network.RetrofitService
import io.github.gusandrianos.foxforreddit.utilities.FoxToolkit.getBearer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SubredditRepository {
    private val redditAPI: RedditAPI = RetrofitService.getRedditAPIInstance()

    fun getSubredditLinkFlair(subredditName: String, application: Application): LiveData<List<Flair>> {
        val subredditLinkFlair: MutableLiveData<List<Flair>> = MutableLiveData()
        val bearer = getBearer(application)
        val subLinkFlairCall = redditAPI.getSubredditLinkFlair(bearer, subredditName)
        subLinkFlairCall.enqueue(object : Callback<List<Flair>> {
            override fun onResponse(call: Call<List<Flair>>, response: Response<List<Flair>>) {
                if (response.isSuccessful)
                    subredditLinkFlair.value = response.body()
            }

            override fun onFailure(call: Call<List<Flair>>, t: Throwable) {
            }

        })
        return subredditLinkFlair
    }

    fun getSubreddit(subredditName: String, application: Application): LiveData<Data> {
        val subreddit: MutableLiveData<Data> = MutableLiveData()

        val bearer = getBearer(application)
        val aboutCall = redditAPI.getSubreddit(bearer, subredditName)
        aboutCall.enqueue(object : Callback<Thing> {
            override fun onResponse(call: Call<Thing>, response: Response<Thing>) {
                if (response.isSuccessful)
                    subreddit.value = response.body()?.data
            }

            override fun onFailure(call: Call<Thing>, t: Throwable) {
            }
        })
        return subreddit
    }

    fun getSubredditWiki(subredditName: String, application: Application): LiveData<Data> {
        val subredditWiki: MutableLiveData<Data> = MutableLiveData()
        val bearer = getBearer(application)
        val wikiCall = redditAPI.getSubredditWiki(bearer, subredditName)
        wikiCall.enqueue(object : Callback<Thing> {
            override fun onResponse(call: Call<Thing>, response: Response<Thing>) {
                if (response.isSuccessful)
                    subredditWiki.value = response.body()?.data
            }

            override fun onFailure(call: Call<Thing>, t: Throwable) {
            }
        })
        return subredditWiki
    }

    fun getSubredditRules(subredditName: String, application: Application): LiveData<RulesBundle> {
        val subredditRules: MutableLiveData<RulesBundle> = MutableLiveData()
        val bearer = getBearer(application)
        val rulesCall = redditAPI.getSubredditRules(bearer, subredditName)
        rulesCall.enqueue(object : Callback<RulesBundle> {
            override fun onResponse(call: Call<RulesBundle>, response: Response<RulesBundle>) {
                if (response.isSuccessful)
                    subredditRules.value = response.body()
            }

            override fun onFailure(call: Call<RulesBundle>, t: Throwable) {
            }
        })
        return subredditRules
    }

    fun getSubredditModerators(subredditName: String, application: Application): LiveData<ModeratorsList> {
        val subredditModerators: MutableLiveData<ModeratorsList> = MutableLiveData()
        val bearer = getBearer(application)
        val modsCall = redditAPI.getSubredditModerators(bearer, subredditName)
        modsCall.enqueue(object : Callback<ModeratorsResponse> {
            override fun onResponse(call: Call<ModeratorsResponse>, response: Response<ModeratorsResponse>) {
                if (response.isSuccessful)
                    subredditModerators.value = response.body()?.modList
            }

            override fun onFailure(call: Call<ModeratorsResponse>, t: Throwable) {
            }
        })
        return subredditModerators
    }

    fun toggleSubscribed(action: Int, subredditName: String, application: Application): LiveData<Boolean> {
        val subscribeStatus: MutableLiveData<Boolean> = MutableLiveData()
        val bearer = getBearer(application)
        var actionString = ""
        if (action == ACTION_SUBSCRIBE)
            actionString = "sub"
        else if (action == ACTION_UNSUBSCRIBE)
            actionString = "unsub"

        val subUnsubCall = redditAPI.toggleSubscribe(bearer, actionString, subredditName)
        subUnsubCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                subscribeStatus.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("SUBREDDIT REPOSITORY", "toggleSubscribed() onFailure: ${t.message}")
            }
        })
        return subscribeStatus
    }
}
