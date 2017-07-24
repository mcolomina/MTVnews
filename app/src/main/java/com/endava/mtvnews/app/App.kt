package com.endava.mtvnews.app

import android.app.Application
import com.endava.mtvnews.net.NetModule

open class App : Application() {

    open val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }

}

