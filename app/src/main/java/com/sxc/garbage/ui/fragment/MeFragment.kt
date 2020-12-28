package com.sxc.garbage.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.database.OrderInfoBase
import com.sxc.garbage.ui.activity.EditAddressActivity
import com.sxc.garbage.ui.activity.RecorderActivity
import com.sxc.garbage.ui.activity.SettingsActivity
import kotlinx.android.synthetic.main.fragment_me.*


class MeFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        //初始化数据库
        var room = Room.databaseBuilder(requireActivity(), OrderInfoBase::class.java, "orderinfo")
            .allowMainThreadQueries().build()
        var orderInfoDao = room.orderInfoDao()
        var noworder = orderInfoDao?.getState(1)
        var cancelorder = orderInfoDao?.getState(2)
        var hisorder = orderInfoDao?.getState(3)
        var nownum = noworder?.size
        var hisnum = hisorder?.size
        var cancelnum = cancelorder?.size

        var total_num = nownum!! + hisnum!! + cancelnum!!
        //设置性别
        tv_total_num.text = nownum.toString()//当前订单
        tv_rub_num.text = hisnum.toString()  //历史订单 已完成
        tv_passive_rub_num.text = total_num.toString() //总数

        //投诉
        tv_complaint.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            val data: Uri = Uri.parse("tel:$19910781914")
            intent.data = data
            startActivity(intent)
        }

        //设置
        tv_setting.setOnClickListener {
            var intent = Intent(requireActivity(), SettingsActivity::class.java)
            startActivity(intent)

        }
        //设置
        tv_zhengming.setOnClickListener {
            Toast.makeText(requireActivity(), "该功能待完善", Toast.LENGTH_LONG).show()
        }

        //订单详情
        tv_trip_recorder.setOnClickListener {
            var intent = Intent(requireActivity(), RecorderActivity::class.java)
            startActivity(intent)
        }
        //订单详情
        tv_my_info.setOnClickListener {
            var intent = Intent(requireActivity(), EditAddressActivity::class.java)
            startActivity(intent)
        }
        //设置性别
//        iv_gender


    }
}