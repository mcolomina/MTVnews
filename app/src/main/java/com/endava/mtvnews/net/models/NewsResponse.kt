package com.endava.mtvnews.net.models

import com.google.gson.annotations.SerializedName

class NewsResponse {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("source")
    var source: String? = null

    @SerializedName("sortBy")
    var sortBy: String? = null

    @SerializedName("articles")
    var newsItem: List<NewsItem> = ArrayList()

}