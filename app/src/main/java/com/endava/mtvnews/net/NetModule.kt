package com.endava.mtvnews.net

import com.endava.mtvnews.BuildConfig
import com.endava.mtvnews.app.App
import com.endava.mtvnews.app.ApplicationScope
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    private val BASE_URL = "https://newsapi.org"

    @Provides
    @ApplicationScope
    fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @ApplicationScope
    fun provideCache(app: App): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @ApplicationScope
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @ApplicationScope
    fun provideRestService(retrofit: Retrofit): RestService {
        return RestService(retrofit)
    }
}