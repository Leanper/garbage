package com.sxc.garbage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sxc.garbage.R
import com.sxc.garbage.entity.OrderInfo

class HisOrderAdapter (context: Context, list: ArrayList<OrderInfo>) : RecyclerView.Adapter<HisOrderAdapter.MyHolder>() {
    var list: ArrayList<OrderInfo>? = null
    var inflater: LayoutInflater? = null
    var context: Context? = null

    init {
        this.list = list
        this.inflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun getItemCount(): Int {
        return list?.size ?:0
    }


    class MyHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView!!) {

        var text = itemView?.findViewById<TextView>(R.id.tv_name)
        var tv_addr = itemView?.findViewById<TextView>(R.id.tv_addr)
        var tv_time = itemView?.findViewById<TextView>(R.id.tv_time)
        var tv_phonenum = itemView?.findViewById<TextView>(R.id.tv_phonenum)
        var tv_ordernum = itemView?.findViewById<TextView>(R.id.tv_ordernum)
        var tv_info = itemView?.findViewById<TextView>(R.id.tv_info)
        var ll_item = itemView?.findViewById<LinearLayout>(R.id.ll_item)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder?.text?.text = list?.get(position)?.name
        holder?.tv_addr?.text = list?.get(position)?.address
        holder?.tv_time?.text = list?.get(position)?.time
        holder?.tv_phonenum?.text = list?.get(position)?.phoneNum
        holder?.tv_ordernum?.text = list?.get(position)?.orderNum.toString()

        when(list?.get(position)?.state){
            1->holder?.tv_info?.text ="当前订单"
            2->holder?.tv_info?.text ="取消订单"
            3->holder?.tv_info?.text ="历史订单"
        }

        holder.tv_info?.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position);
            }
        }

        holder.ll_item?.setOnClickListener {
            if (listenerinfo != null) {
                listenerinfo!!.onClick(position);
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(inflater?.inflate(R.layout.item_orderinfolayout, parent, false), context!!)

    }

    //第一步 定义接口
    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    //第二步， 写一个公共的方法
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }


    //第一步 定义接口
    interface OnIteminfoClickListener {
        fun onClick(position: Int)
    }

    private var listenerinfo: OnIteminfoClickListener? = null

    //第二步， 写一个公共的方法
    fun setOnItemInfoClickListener(listener: OnIteminfoClickListener?) {
        this.listenerinfo = listenerinfo
    }


}