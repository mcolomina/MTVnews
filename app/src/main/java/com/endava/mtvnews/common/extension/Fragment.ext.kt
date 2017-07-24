package com.endava.mtvnews.common.extension

import android.support.v4.app.Fragment
import com.endava.mtvnews.app.App


val Fragment.app: App
    get() = activity.application as App
