package com.sxc.garbage.ui.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.room.Room
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.LocationSource.OnLocationChangedListener
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.*
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult


import com.bigkoo.pickerview.TimePickerView
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.dao.OrderInfoDao
import com.sxc.garbage.database.OrderInfoBase
import com.sxc.garbage.database.UserInfoDataBase
import com.sxc.garbage.entity.OrderInfo
import com.sxc.garbage.ui.activity.EditAddressActivity
import kotlinx.android.synthetic.main.fragment_release.*
import java.text.SimpleDateFormat
import java.util.*


class ReleaseFragment :BaseFragment() {




    override fun getLayoutId(): Int {
        return R.layout.fragment_release
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            initMapView(savedInstanceState)
        }
        initView()

    }

    private fun initView() {



        //初始化数据库
        var room= Room.databaseBuilder(requireActivity(), OrderInfoBase::class.java,"orderinfo").allowMainThreadQueries().build()
        var orderInfoDao=room.orderInfoDao()

        var user= Room.databaseBuilder(requireActivity(), UserInfoDataBase::class.java,"room").allowMainThreadQueries().build()
        var userInfoDao=user.userInfoDao()
        var userinfo=userInfoDao?.getFirst()

        var info=orderInfoDao?.getAll()

        Log.i("TAG","${info.toString()}")

        //编辑地址
        et_location.setOnClickListener {
            var intent=Intent(requireActivity(),EditAddressActivity::class.java)
            startActivity(intent)
        }

        bt_nowrelease.setOnClickListener {
            et_infolocation.checkBlank("发送人不能为空")
            if (orderInfoDao != null) {
                if (userinfo?.phoneNum!=null){
                    release(orderInfoDao)

                }else{
                    Toast.makeText(requireActivity(),"请输入发件人详细信息",Toast.LENGTH_LONG).show()
                }
                et_infolocation.checkBlank("发送人不能为空")
            }
        }


        tv_time.setOnClickListener {
            chooseTime()
        }

    }


    fun initMapView(savedInstanceState:Bundle){

        //初始化地图
        mapview.onCreate(savedInstanceState)
        val aMap: AMap = mapview.getMap()
        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式

        val myLocationStyle: MyLocationStyle
        myLocationStyle = MyLocationStyle() //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。

        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        aMap.myLocationStyle = myLocationStyle //设置定位蓝点的Style

        aMap.isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12F))



    }

    fun chooseTime(){

        //时间选择器
        val pvTime: TimePickerView = TimePickerView.Builder(requireActivity(), object : TimePickerView.OnTimeSelectListener{
            override fun onTimeSelect(date: Date?, v: View?) { //选中事件回调
                tv_releasetime.text= date?.let { getTime(it) }
            }
        })
            .build()
        pvTime.setDate(Calendar.getInstance()) //注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show()
    }


    private fun getTime(date: Date): String? { //可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }
    /*
    * 立即下单
    * */
    fun release(orderInfoDao:OrderInfoDao){
        var nowTime=System.currentTimeMillis()

        var data=SimpleDateFormat.getDateInstance().format(Date())

        var orderInfo=OrderInfo()
        var room= Room.databaseBuilder(requireActivity(), UserInfoDataBase::class.java,"room").allowMainThreadQueries().build()
        var user=room.userInfoDao()
        var userinfo=user?.getFirst()
        orderInfo.address=et_location.text.toString().trim()
        orderInfo.state=1
        orderInfo.address=userinfo?.address
        orderInfo.name=et_infolocation.text.toString().trim()
        orderInfo.phoneNum=userinfo?.phoneNum
        orderInfo.time=tv_releasetime.text.toString()
        orderInfo.orderNum=nowTime

        orderInfoDao?.insert(orderInfo)
    }









}


