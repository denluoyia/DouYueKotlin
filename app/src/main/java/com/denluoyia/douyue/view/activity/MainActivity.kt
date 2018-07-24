package com.denluoyia.douyue.view.activity

import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import butterknife.OnClick
import com.denluoyia.douyue.R
import com.denluoyia.douyue.base.BaseActivity
import com.denluoyia.douyue.view.adapter.FragmentListAdapter
import com.denluoyia.douyue.view.fragment.ItemListFragment
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
                Toast.makeText(this, "iv_toggle", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


