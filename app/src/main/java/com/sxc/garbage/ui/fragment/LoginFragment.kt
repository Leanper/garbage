package com.sxc.garbage.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.database.UserInfoDataBase
import com.sxc.garbage.ui.utils.ShareUtils
import kotlinx.android.synthetic.main.fragment_login.*

/*
* login
* */
class LoginFragment:BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView(rootview: View?) {
        super.initView(rootview)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var room= Room.databaseBuilder(requireActivity(), UserInfoDataBase::class.java,"room").allowMainThreadQueries().build()
        var userInfoDao=room.userInfoDao()

        var myuserInfo=userInfoDao?.getAll()
        Log.d("TAG",myuserInfo.toString())
        //登录点击事件处理
        bt_login.setOnClickListener {
            Log.d("TAG",myuserInfo.toString())
            Log.d("TAG","吉和长度为 ${myuserInfo?.size}")
            var password=et_password.text.toString().trim()
            var username=et_username.text.toString().trim()
            et_username.checkBlank("用户名为空")
            et_password.checkBlank("密码为空")


            myuserInfo?.forEach {
                if(it?.name==username&&it?.password==password){
                    findNavController().navigate(R.id.mainActivity)
                    Toast.makeText(requireActivity(),"登录成功", Toast.LENGTH_LONG).show()

                    requireActivity().finish()
                }else{
                    Toast.makeText(requireActivity(),"登录信息错误请验证", Toast.LENGTH_LONG).show()

                }
            }


        }

        //忘记密码
        tv_forgetpassword.setOnClickListener {
            findNavController().navigate(R.id.forgetPassFragment)
        }
        //注册
        tv_register.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

    }
}