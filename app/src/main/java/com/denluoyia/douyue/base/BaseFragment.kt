package com.denluoyia.douyue.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by denluoyia
 * Date 2018/07/20
 * DouYue
 */
abstract class BaseFragment : Fragment(){

    val TAG : String? = this.javaClass.simpleName
    private lateinit var unBinder : Unbinder

    abstract fun setLayoutContentViewId() : Int
    abstract fun doBusiness()

    var mRootView : View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var contentViewId : Int = setLayoutContentViewId()
        if (contentViewId == 0) throw IllegalArgumentException("must set fragment's contentView!")
        if (mRootView != null){
            var parent : ViewGroup? = mRootView!!.parent as ViewGroup
            if (null != parent){
                parent.removeView(mRootView)
            }
        }
        mRootView = inflater.inflate(contentViewId, container, false)
        unBinder = ButterKnife.bind(this, mRootView!!)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.doBusiness()
    }

    override fun onDestroyView() {
        unBinder.unbind()
        super.onDestroyView()
    }
}