package com.sxc.garbage.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sxc.garbage.R
import com.sxc.garbage.adapter.HisOrderAdapter
import com.sxc.garbage.adapter.OrderInfoAdapter
import com.sxc.garbage.base.BaseActivity
import com.sxc.garbage.database.OrderInfoBase
import com.sxc.garbage.entity.OrderInfo
import kotlinx.android.synthetic.main.activity_recorder.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class RecorderActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_recorder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recorder)
        initView()
    }

    private fun initView() {
        tv_toolbar_title.text="订单记录"
        //初始化数据库
        var room= Room.databaseBuilder(this, OrderInfoBase::class.java,"orderinfo").allowMainThreadQueries().build()
        var orderInfoDao=room.orderInfoDao()

        var info=orderInfoDao?.getAll()
        Log.d("TAG","${info.toString()}")
        if (info?.size!! >0){
            placeholder1.visibility= View.GONE
            //展示列表
            rv_record.layoutManager = LinearLayoutManager(this)

            //初始化adapter
            var myAdapter = HisOrderAdapter(this, info as ArrayList<OrderInfo>)

            rv_record.adapter = myAdapter




        }else{
            placeholder1.visibility= View.VISIBLE
            rv_record.visibility= View.GONE
        }



    }

}