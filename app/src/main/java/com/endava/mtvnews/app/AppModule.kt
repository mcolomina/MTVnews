package com.endava.mtvnews.app

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    @ApplicationScope
    fun provideApp(): App = app
}