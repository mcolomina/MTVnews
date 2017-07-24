package com.endava.mtvnews.common.utils

import android.text.TextUtils
import android.widget.ImageView
import com.endava.mtvnews.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

class PicassoWrapper constructor(private val picasso: Picasso) {

    protected fun displayImage(requestCreator: RequestCreator, imageView: ImageView) {
        requestCreator.into(imageView)
    }

    fun display(url: String?, viewToDisplay: ImageView) {
        val requestCreator: RequestCreator
        if (TextUtils.isEmpty(url)) {
            requestCreator = picasso.load(R.drawable.ic_placeholder_mtv)
        } else {
            requestCreator = picasso.load(url)
        }
        displayImage(requestCreator.placeholder(R.drawable.ic_placeholder_mtv).error(R.drawable.ic_placeholder_mtv),
                viewToDisplay)
    }
}