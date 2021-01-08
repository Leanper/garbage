package com.sxc.garbage.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

abstract class BaseFragment:Fragment() {

    /*
    * 加载布局
    * */
    abstract fun getLayoutId():Int
    private var mDataStorePre: DataStore<Preferences>? = null
    private val DATASTORE_PREFERENCE_NAME = "DataStorePreference"//定义 DataStore 的名字

    override  fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          var rootview=inflater.inflate(getLayoutId(),container,false)
       //初始化控件
        initView(rootview)

        return rootview
    }

    open  fun initView(rootview: View?){
//初始化数据库
        mDataStorePre = requireActivity().createDataStore(
            name = DATASTORE_PREFERENCE_NAME
        )
    }



    fun TextView.checkBlank(message: String): String? {
        val text = this.text.toString()
        if (text.isBlank()) {
            Toast.makeText(requireActivity(),message,Toast.LENGTH_LONG).show()
            return null
        }
        return text
    }

}