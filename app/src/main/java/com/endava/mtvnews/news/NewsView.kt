package com.endava.mtvnews.news

import com.endava.mtvnews.net.models.NewsItem

interface NewsView {
    fun showEmptyListMsg()
    fun updateList(newsItem: List<NewsItem>)
    fun showError()
}