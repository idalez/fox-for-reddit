package io.github.gusandrianos.foxforreddit.data.models

import com.google.gson.annotations.SerializedName

class Media {
    @SerializedName("reddit_video")
    val redditVideo: RedditVideo? = null

    @SerializedName("oembed")
    val oembed: Oembed? = null

    @SerializedName("type")
    val type: String? = null
}