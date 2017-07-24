package com.endava.mtvnews.news.di

import com.endava.mtvnews.news.NewsFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = arrayOf(NewsModule::class))
interface NewsComponent {

    fun inject(fragment: NewsFragment)
}