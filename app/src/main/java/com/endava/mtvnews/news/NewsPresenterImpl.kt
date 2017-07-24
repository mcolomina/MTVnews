package com.endava.mtvnews.news

import android.content.Context
import com.endava.mtvnews.R
import com.endava.mtvnews.net.RestService
import com.endava.mtvnews.net.models.NewsResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class NewsPresenterImpl(private val view: NewsView, private val restService: RestService, private val context: Context) : NewsPresenter {
    private val compositeSubscription: CompositeSubscription = CompositeSubscription()

    override fun loadNews() {
        val source: String = context.getString(R.string.api_source)
        val sortBy: String = context.getString(R.string.api_sort_by)
        val apiKey: String = context.getString(R.string.api_key)
        compositeSubscription.add(
                restService.getNews(source, sortBy, apiKey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleSuccessResult, this::handleErrorResponse))
    }

    private fun handleSuccessResult(response: NewsResponse) {
        if (response == null || response.newsItem.size == 0) view.showEmptyListMsg()
        view.updateList(response.newsItem)
    }


    private fun handleErrorResponse(throwable: Throwable) {
        view.showError();
    }

    override fun onDestroy() {
        compositeSubscription.clear()
    }
}

