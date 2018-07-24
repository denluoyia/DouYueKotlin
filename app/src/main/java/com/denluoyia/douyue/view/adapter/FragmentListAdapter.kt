package com.denluoyia.douyue.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by denluoyia
 * Date 2018/07/24
 * DouYue
 */
class FragmentListAdapter : FragmentStatePagerAdapter {

    var mFragmentList : ArrayList<Fragment> = ArrayList()

    constructor(fragmentList : ArrayList<Fragment>, fm : FragmentManager) : super(fm) {
        mFragmentList.clear()
        if (fragmentList != null && fragmentList.size > 0)
        this.mFragmentList.addAll(fragmentList)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}