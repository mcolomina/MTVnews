package com.endava.mtvnews.net

import com.endava.mtvnews.net.models.NewsResponse
import retrofit2.Retrofit
import rx.Observable

class RestService(retrofit: Retrofit) {

    private val apiService: ApiService = retrofit.create<ApiService>(ApiService::class.java)

    fun getNews(source: String, sortBy: String, apiKey: String): Observable<NewsResponse> {
        return apiService.getNews(source, sortBy, apiKey)
    }
}