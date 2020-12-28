package com.sxc.garbage.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.room.Room
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseActivity
import com.sxc.garbage.dao.UserInfoDao
import com.sxc.garbage.database.UserInfoDataBase
import com.sxc.garbage.entity.UserInfo
import kotlinx.android.synthetic.main.activity_edit_address.*
import kotlinx.android.synthetic.main.fragment_release.*


class EditAddressActivity : BaseActivity() {
    var csex:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)
        init()

    }

    fun init(){
        var room=Room.databaseBuilder(this, UserInfoDataBase::class.java,"room").allowMainThreadQueries().build()
        var userInfoDao=room.userInfoDao()


        //返回事件处理
        iv_back.setOnClickListener {
            finish()
        }
        bt_saveaddr.setOnClickListener {

            userInfoDao?.let { saveaddress(it) }
        }

        tv_chooseadd.setOnClickListener{
            var intent=Intent(this,ChooseAddrActivity::class.java)
            startActivity(intent)
        }

        radiogroup.setOnCheckedChangeListener { group, checkedId ->
            Log.d("TAG","tag--- $checkedId")
            if (checkedId===radioman.id){
                csex=1
            }else if (checkedId===radiowen.id){
                csex=0
            }

        }


    }

    private fun saveaddress(userInfoDao:UserInfoDao) {
        et_num.checkBlank("门牌号不能为空")
        et_PersonName.checkBlank("姓名不能为空")
        et_phone.checkBlank("电话不能为空")

        //获取参数
        var personname=et_PersonName.text.toString().trim()
        var personadd=et_num.text.toString().trim()
        var personphone=et_phone.text.toString().trim()
        var user=UserInfo()
        user.lastname=personname
        user.sex=csex
        user.phoneNum=personphone
        user.address=personadd
        var info=userInfoDao.getFirst()
        user.id= info?.id!!
        user.password=info.password
        user.name=info.name
        userInfoDao.update(user)
        Log.d("TAG","tag--- ${user.toString()}")

        finish()
    }






}