package com.sxc.garbage.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseActivity
import kotlinx.android.synthetic.main.settings_activity.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SettingsActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        init()


    }

    private fun init() {
        tv_holp.setOnClickListener(this)
        tv_update.setOnClickListener(this)
        tv_complaint.setOnClickListener(this)
        tv_aboutme.setOnClickListener(this)
        tv_changepass.setOnClickListener(this)
        bt_outlogin.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        tv_toolbar_title.text="设置"

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_holp-> Toast.makeText(this,"该功能正在完善中",Toast.LENGTH_LONG).show()
            R.id.tv_update-> Toast.makeText(this,"该功能正在完善中",Toast.LENGTH_LONG).show()
            R.id.tv_complaint-> Toast.makeText(this,"该功能正在完善中",Toast.LENGTH_LONG).show()
            R.id.tv_aboutme-> Toast.makeText(this,"该功能正在完善中",Toast.LENGTH_LONG).show()
            R.id.tv_changepass-> Toast.makeText(this,"该功能正在完善中",Toast.LENGTH_LONG).show()
            R.id.iv_back->finish()
            R.id.bt_outlogin-> {
            var intent=Intent(this,WelcomeActivity::class.java)
                startActivity(intent)
            }
            }
        }



}