package com.endava.mtvnews.app

import com.endava.mtvnews.net.NetModule
import com.endava.mtvnews.news.di.NewsComponent
import com.endava.mtvnews.news.di.NewsModule
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(
        AppModule::class,
        NetModule::class))
interface AppComponent {

    fun plus(module: NewsModule): NewsComponent
}