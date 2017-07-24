package com.endava.mtvnews.common.extension

import android.support.v7.app.AppCompatActivity
import com.endava.mtvnews.app.App
import kotlinx.android.synthetic.main.activity_main.*

val AppCompatActivity.app: App
    get() = application as App

fun AppCompatActivity.initToolbar(title: String? = null, hasParent: Boolean = false) {
    setSupportActionBar(main_toolbar)

    title?.let {
        supportActionBar?.title = it
    }

    if (hasParent) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
