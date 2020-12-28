package com.sxc.garbage.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amap.api.maps.AMap
import com.permissionx.guolindev.PermissionX
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_release.*


class ChooseAddrActivity : BaseActivity() {

    private val NOT_NOTICE = 2 //如果勾选了不再询问
    private var alertDialog: AlertDialog? = null
    private var mDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_addr)

        inits(savedInstanceState)

    }

     fun inits(savedInstanceState:Bundle?) {

         myRequetPermission()


        //初始化地图
        mapview.onCreate(savedInstanceState)
        val aMap: AMap = mapview.getMap()
        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式





    }


    private fun myRequetPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        } else {
            Toast.makeText(this, "您已经申请了权限!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode === 1) {
            for (i in 0 until permissions.size) {
                if (grantResults[i] === PERMISSION_GRANTED) { //选择了“始终允许”
                    Toast.makeText(
                        this,
                        "" + "权限" + permissions[i] + "申请成功",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            permissions[i]
                        )
                    ) { //用户选择了禁止不再询问
                        val builder: AlertDialog.Builder =
                            AlertDialog.Builder(this)
                        builder.setTitle("permission")
                            .setMessage("点击允许才可以使用我们的app哦")
                            .setPositiveButton(
                                "去允许",
                                DialogInterface.OnClickListener { dialog, id ->
                                    if (mDialog != null && mDialog!!.isShowing()) {
                                        mDialog!!.dismiss()
                                    }
                                    val intent =
                                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                    val uri: Uri = Uri.fromParts(
                                        "package",
                                        packageName,
                                        null
                                    ) //注意就是"package",不用改成自己的包名
                                    intent.data = uri
                                    startActivityForResult(intent, NOT_NOTICE)
                                })
                        mDialog = builder.create()
                        mDialog?.setCanceledOnTouchOutside(false)
                        mDialog?.show()
                    } else { //选择禁止
                        val builder: AlertDialog.Builder =
                            AlertDialog.Builder(this)
                        builder.setTitle("permission")
                            .setMessage("点击允许才可以使用我们的app哦")
                            .setPositiveButton(
                                "去允许",
                                DialogInterface.OnClickListener { dialog, id ->
                                    if (alertDialog != null && alertDialog!!.isShowing()) {
                                        alertDialog!!.dismiss()
                                    }
                                    ActivityCompat.requestPermissions(
                                        this,
                                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                        1
                                    )
                                })
                        alertDialog = builder.create()

                        alertDialog?.setCanceledOnTouchOutside(false)
                        alertDialog?.show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NOT_NOTICE) {
            myRequetPermission() //由于不知道是否选择了允许所以需要再次判断
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapview.onDestroy()
    }

}