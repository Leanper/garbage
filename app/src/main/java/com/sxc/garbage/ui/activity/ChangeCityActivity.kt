package com.sxc.garbage.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.lifecycleScope
import com.sxc.garbage.R
import com.sxc.garbage.adapter.CityListAdapter
import com.sxc.garbage.base.BaseActivity
import com.sxc.garbage.bean.City
import com.sxc.garbage.bean.CityPickerBean
import com.sxc.garbage.bean.LocateState
import com.sxc.garbage.ui.utils.GsonUtil
import com.sxc.garbage.ui.utils.PinyinUtils
import com.sxc.garbage.ui.utils.ReadAssetsFileUtil
import com.sxc.garbage.ui.view.SideLetterBar
import kotlinx.android.synthetic.main.activity_change_city.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.coroutines.launch
import java.util.*

class ChangeCityActivity : BaseActivity() {
    private lateinit var mCityAdapter: CityListAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_change_city
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_change_city)
        init()
    }

    private fun init() {
        tv_toolbar_title.text="选择城市"

        side_letter_bar.setOverlay(tv_letter_overlay)
        side_letter_bar.setOnLetterChangedListener(object : SideLetterBar.OnLetterChangedListener {
            override fun onLetterChanged(letter: String?) {
                val position: Int = mCityAdapter.getLetterPosition(letter)
                listview_all_city.setSelection(position)
            }
        })
        mCityAdapter = CityListAdapter(this@ChangeCityActivity)
        initData()
        listview_all_city.setAdapter(mCityAdapter)


    }


    protected  fun initData() {
        getCityData()
        mCityAdapter.setOnCityClickListener(object : CityListAdapter.OnCityClickListener {
            override  fun onCityClick(name: String?) { //选择城市
                Toast.makeText(this@ChangeCityActivity, name, Toast.LENGTH_SHORT).show()
                if (name != null) {

                }

            }

            override fun onLocateClick() { //点击定位按钮
                mCityAdapter.updateLocateState(LocateState.LOCATING, null)
            }
        })
    }

    fun getCityData() {
        val json = ReadAssetsFileUtil.getJson(this, "city.json")

        val bean: CityPickerBean = GsonUtil.getBean(json, CityPickerBean::class.java)
        val citys = HashSet<City?>()
        for (areasBean in bean.data.areas) {
            val name: String = areasBean.name.replace("　", "")
            citys.add(City(areasBean.id, name, PinyinUtils.getPinYin(name), areasBean.is_hot === 1))
            for (childrenBeanX in areasBean.children) {
                citys.add(
                    City(
                        childrenBeanX.id,
                        childrenBeanX.name,
                        PinyinUtils.getPinYin(childrenBeanX.name),
                        childrenBeanX.is_hot === 1
                    )
                )
            }
        }
        //set转换list
        val cities: ArrayList<City> = ArrayList<City>(citys)


        Collections.sort(cities, object : Comparator<City> {
             override fun compare(city: City, t1: City): Int {
                return city.getPinyin().compareTo(t1.getPinyin())
            }
        })
        mCityAdapter.setData(cities)
    }


}