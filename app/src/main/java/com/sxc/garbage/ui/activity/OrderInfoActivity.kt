package com.sxc.garbage.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseActivity


class OrderInfoActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_order_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_info)

        init()
    }

    private fun init() {
        val intent = intent
        var position=intent.getIntExtra("position",0)


    }
}