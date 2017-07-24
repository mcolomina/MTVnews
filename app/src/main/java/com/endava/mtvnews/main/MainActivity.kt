package com.endava.mtvnews.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.endava.mtvnews.R
import com.endava.mtvnews.common.extension.initToolbar
import com.endava.mtvnews.news.NewsFragment

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        var newsFragment = supportFragmentManager.findFragmentByTag(NewsFragment.TAG)
        if (newsFragment == null) {
            newsFragment = NewsFragment()
            newsFragment.mainView = this
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, newsFragment, NewsFragment.TAG)
                    .commit()
        }
    }

    override fun openUrl(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}
