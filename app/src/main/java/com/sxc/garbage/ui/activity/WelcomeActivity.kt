package com.sxc.garbage.ui.activity

import android.os.Bundle
import android.view.View
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_layout.*

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        iv_back.visibility= View.GONE
        tv_toolbar_title.text="登录"

        
    }
}