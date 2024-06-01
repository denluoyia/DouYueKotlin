package com.denluoyia.douyuekotlin.view.activity

import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import butterknife.OnClick
import com.denluoyia.douyuekotlin.R
import com.denluoyia.douyuekotlin.base.BaseActivity
import com.denluoyia.douyuekotlin.view.adapter.FragmentListAdapter
import com.denluoyia.douyuekotlin.view.fragment.ItemListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun doBusiness() {
        mainTitleTabView.bindViewPager(view_pager)
        var fragments : ArrayList<Fragment> = ArrayList()
        fragments.add(ItemListFragment.newInstance(1))
        fragments.add(ItemListFragment.newInstance(3))
        fragments.add(ItemListFragment.newInstance(2))
        view_pager.adapter = FragmentListAdapter(fragments, supportFragmentManager)
        view_pager.offscreenPageLimit = fragments.size
        view_pager.currentItem = 0
    }

    @OnClick(R.id.iv_toggle, R.id.ic_search)
    fun onClick(view : View){
        when(view.id){
            R.id.iv_toggle -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private var touchTime : Long = 0
    override fun onBackPressed() {
        var currentTime = System.currentTimeMillis()
        if (currentTime - touchTime < 2000){
            super.onBackPressed()
        }
        touchTime = currentTime
        Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show()
    }
}


