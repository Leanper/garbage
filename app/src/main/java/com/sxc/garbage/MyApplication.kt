package com.sxc.garbage

import android.app.Application
import android.content.Context

class MyApplication :Application(){

    private var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        mContext = getApplicationContext();
    }

    open fun getContext(): Context? {
        return mContext
    }

}

