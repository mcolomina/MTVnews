package com.endava.mtvnews.net

import com.endava.mtvnews.net.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiService {

    @GET("/v1/articles")
    fun getNews(@Query("source") source: String, @Query("sortBy") sortBy: String, @Query("apiKey") apiKey: String): Observable<NewsResponse>
}