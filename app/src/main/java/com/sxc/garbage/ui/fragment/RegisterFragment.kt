package com.sxc.garbage.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.database.UserInfoDataBase
import com.sxc.garbage.entity.UserInfo
import kotlinx.android.synthetic.main.fragment_register.*


/*
* login
* */
class RegisterFragment:BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_register
    }

    override fun initView(rootview: View?) {
        super.initView(rootview)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var room=Room.databaseBuilder(requireActivity(),UserInfoDataBase::class.java,"room").allowMainThreadQueries().build()
        var userInfoDao=room.userInfoDao()

        //注册按钮点击事件
        bt_login.setOnClickListener {

            // 优雅地判空
            val username = et_username.checkBlank("手机号不能为空") ?: return@setOnClickListener
            val password = et_password.checkBlank("密码不能为空") ?: return@setOnClickListener


            if(password!=null&&username!=null){

                var userInfo= UserInfo()
                userInfo.name=username
                userInfo.password=password
                userInfoDao?.insert(userInfo)

                Toast.makeText(requireActivity(),"注册成功",Toast.LENGTH_LONG).show()

                findNavController().popBackStack()
            }


        }




    }
}