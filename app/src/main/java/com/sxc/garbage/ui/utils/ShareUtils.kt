package com.sxc.garbage.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.sxc.garbage.MyApplication
import java.security.AccessController.getContext

class ShareUtils {
    var SP_NAME = "sxc"
    private var sp: SharedPreferences? = null

    /**
     * SharedPreferences 保存boolean
     *
     * @param key
     * @param value
     */
    open fun saveBoolean(context: Context,key: String?, value: Boolean) {
        if (sp == null) {
            sp = context
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        sp?.edit()?.putBoolean(key, value)?.commit()
    }

    open fun getBoolean(context: Context,key: String?, defValue: Boolean): Boolean? {
        if (sp == null) {
            sp = context
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return sp?.getBoolean(key, defValue)
    }


    /**
     * SharedPreferences 保存字符串
     *
     * @param key
     * @param value
     */
    open fun saveString(context: Context,key: String?, value: String?) {
        if (sp == null) {
            sp = context
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        sp?.edit()?.putString(key, value)?.commit()
    }

    open fun getString(context: Context,key: String?, defValue: String?): String? {
        if (sp == null) {
            sp = context
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return sp?.getString(key, defValue)
    }


}
