package com.sxc.garbage.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.sxc.garbage.adapter.CityListAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


abstract class BaseActivity: AppCompatActivity() {

    abstract fun getLayoutId():Int
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())


    }

    fun TextView.checkBlank(message: String): String? {
        val text = this.text.toString()
        if (text.isBlank()) {
            Toast.makeText(this@BaseActivity,message.toString(), Toast.LENGTH_LONG).show()
            return null
        }
        return text
    }






}