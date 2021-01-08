package com.sxc.garbage.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import com.sxc.garbage.R
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.ui.activity.ChangeCityActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment :BaseFragment() {

    private val tabs = arrayOf("当前订单", "历史订单","取消订单")


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //跳转到切换城市界面
        tv_location.setOnClickListener {
          var intent=Intent(requireActivity(),ChangeCityActivity::class.java)
            startActivity(intent)
        }

        var myfragemts=mutableListOf<Fragment>()
        myfragemts.add(NowRecoderFragment())
        myfragemts.add(HistoryRecoderFragment())
        myfragemts.add(CancelRecoderFragment())

        tablayout.addTab(tablayout.newTab())

        vp_recoder.adapter=(object : FragmentPagerAdapter(requireActivity().supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return myfragemts.get(position)
            }

            override fun getCount(): Int {
                return myfragemts.size
            }

            @Nullable
            override fun getPageTitle(position: Int): CharSequence? {
                return tabs.get(position)
            }
        })

        tablayout.setupWithViewPager(vp_recoder)
        tablayout.setOnClickListener {

        }

    }





}