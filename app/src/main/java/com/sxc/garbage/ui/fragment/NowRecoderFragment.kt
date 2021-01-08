package com.sxc.garbage.ui.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sxc.garbage.R
import com.sxc.garbage.adapter.OrderInfoAdapter
import com.sxc.garbage.adapter.OrderInfoAdapter.OnItemClickListener
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.database.OrderInfoBase
import com.sxc.garbage.entity.OrderInfo
import com.sxc.garbage.ui.activity.OrderInfoActivity
import kotlinx.android.synthetic.main.fragment_nowrecoder.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NowRecoderFragment:BaseFragment() {

    private var alertDialog: AlertDialog? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_nowrecoder
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {

        //初始化数据库
        var room= Room.databaseBuilder(requireActivity(), OrderInfoBase::class.java,"orderinfo").allowMainThreadQueries().build()
        var orderInfoDao=room.orderInfoDao()

        var info=orderInfoDao?.getState(1)
        Log.d("TAG","${info.toString()}")
        if (info?.size!! >0){
            placeholder1.visibility=View.GONE
            //展示列表
            rv_record.layoutManager = LinearLayoutManager(requireActivity())

            //初始化adapter
            var myAdapter = OrderInfoAdapter(requireActivity(), info as ArrayList<OrderInfo>)

            rv_record.adapter = myAdapter

            //点击事件
            myAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onClick(position: Int) {

                    val builder: AlertDialog.Builder =
                        AlertDialog.Builder(requireActivity())
                    builder.setTitle("取消")
                        .setMessage("是否取消订单")
                        .setPositiveButton(
                            "确定",
                            DialogInterface.OnClickListener { dialog, id ->
                                if (alertDialog != null && alertDialog!!.isShowing()) {
                                    alertDialog!!.dismiss()
                                    cancelOrder(info[position].id)
                                    myAdapter.notifyDataSetChanged()
                                }

                            }).setNegativeButton("取消",{ dialog, id ->
                            if (alertDialog != null && alertDialog!!.isShowing()) {
                                alertDialog!!.dismiss()
                            }

                        }

                        )
                    alertDialog = builder.create()

                    alertDialog?.setCanceledOnTouchOutside(false)
                    alertDialog?.show()

                }
            })

            //到详情界面
            myAdapter.setOnItemInfoClickListener(object: OrderInfoAdapter.OnIteminfoClickListener {
                override fun onClick(position: Int) {
                    var intent=Intent(requireActivity(),OrderInfoActivity::class.java)
                    intent.putExtra("position",info[position].id)
                    startActivity(intent)
                }

            })


        }else{
            placeholder1.visibility=View.VISIBLE
            rv_record.visibility=View.GONE
        }



    }

    fun cancelOrder(id:Int){
        //初始化数据库
        var room= Room.databaseBuilder(requireActivity(), OrderInfoBase::class.java,"orderinfo").allowMainThreadQueries().build()
        var orderInfoDao=room.orderInfoDao()
            var orderinfo=OrderInfo()
        var changelist=orderInfoDao?.getAllById(id)

        var data= SimpleDateFormat.getDateInstance().format(Date())

        orderinfo.phoneNum=changelist?.phoneNum
        orderinfo.name=changelist?.name
        orderinfo.orderNum= changelist!!.orderNum
        orderinfo.address=changelist?.address
        orderinfo.time=data
        orderinfo.id=changelist?.id
        orderinfo.state=2

        orderInfoDao?.update(orderinfo)

        Log.d("TAG","${orderInfoDao?.getAll()}")


    }
}