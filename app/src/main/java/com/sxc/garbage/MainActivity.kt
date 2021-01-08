package com.sxc.garbage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sxc.garbage.base.BaseFragment
import com.sxc.garbage.ui.fragment.HomeFragment
import com.sxc.garbage.ui.fragment.MeFragment
import com.sxc.garbage.ui.fragment.ReleaseFragment
import com.sxc.garbage.ui.view.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val ID_HOME = 1
        private const val ID_RELEASE = 2
        private const val ID_ME = 3
    }

  var meFragment:MeFragment=MeFragment()
  var homeFragment:HomeFragment=HomeFragment()
  var releaseFragment:ReleaseFragment= ReleaseFragment()

     lateinit var currentFragment :Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        val tran =
            supportFragmentManager.beginTransaction()
        tran.add(R.id.nav_host_fragment, homeFragment)
        tran.add(R.id.nav_host_fragment, releaseFragment).hide(releaseFragment)
        tran.add(R.id.nav_host_fragment, meFragment).hide(meFragment)
        currentFragment = homeFragment
        tran.commit()



        menoBootomView.apply {
            add(
                MeowBottomNavigation.Model(
                    ID_HOME,
                    R.mipmap.icon_home
                )
            )

            add(
                MeowBottomNavigation.Model(
                    ID_RELEASE,
                    R.mipmap.icon_menu_x
                )
            )
            add(
                MeowBottomNavigation.Model(
                    ID_ME,
                    R.mipmap.icon_me
                )
            )

            menoBootomView.setOnClickMenuListener {
                val name = when (it.id) {
                    ID_HOME -> switchFragment(homeFragment)
                    ID_RELEASE ->  switchFragment(releaseFragment)
                    ID_ME ->  switchFragment(meFragment)
                    else -> ""


                }
            }


            setOnReselectListener {

            }

            show(ID_HOME)

        }
        //设置小红点数目
//        menoBootomView.setCount(ID_HOME,"0")




    }



    private fun switchFragment(fragment: BaseFragment) {
        val tran =
            supportFragmentManager.beginTransaction()
        tran.show(fragment)
        tran.hide(currentFragment)
        tran.commit()
        currentFragment = fragment
    }


}