package com.denluoyia.douyuekotlin.presenter

import com.denluoyia.douyuekotlin.base.BasePresenter
import com.denluoyia.douyuekotlin.base.BaseView
import com.denluoyia.douyuekotlin.model.bean.HomeItemBean
import com.denluoyia.douyuekotlin.model.bean.HomeListBean

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
interface ItemListContract {

    interface Presenter : BasePresenter {
        fun loadInitData()
        fun loadMoreData()
    }

    interface ItemListView : BaseView {

        fun loadInitDataSuccess(bean : HomeItemBean)
        fun loadMoreDataSuccess(bean : HomeItemBean)
        fun loadDataFailed(msg : String?)
    }
}