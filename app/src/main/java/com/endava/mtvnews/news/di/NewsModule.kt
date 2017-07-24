package com.endava.mtvnews.news.di;

import com.endava.mtvnews.app.App
import com.endava.mtvnews.common.utils.PicassoWrapper
import com.endava.mtvnews.net.RestService
import com.endava.mtvnews.news.NewsAdapter
import com.endava.mtvnews.news.NewsPresenter
import com.endava.mtvnews.news.NewsPresenterImpl
import com.endava.mtvnews.news.NewsView
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class NewsModule(private val view: NewsView) {

    @Provides
    @FragmentScope
    fun provideNewsPresenter(restService: RestService, app: App): NewsPresenter {
        return NewsPresenterImpl(view, restService, app.applicationContext)
    }

    @Provides
    @FragmentScope
    fun providePicasso(app: App): Picasso {

        return Picasso.with(app.applicationContext)
    }

    @Provides
    @FragmentScope
    fun providePicassoWrapper(picasso: Picasso): PicassoWrapper {
        return PicassoWrapper(picasso)
    }

    @Provides
    @FragmentScope
    fun provideNewsAdapter(picassoWrapper: PicassoWrapper): NewsAdapter {
        return NewsAdapter(picassoWrapper)
    }
}
