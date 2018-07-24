package com.denluoyia.douyue.presenter

import com.denluoyia.douyue.base.BasePresenter
import com.denluoyia.douyue.base.BaseView
import com.denluoyia.douyue.model.bean.HomeItemBean
import com.denluoyia.douyue.model.bean.HomeListBean

/**
 * Created by denluoyia
 * Date 2018/07/23
 * DouYue
 */
interface ItemListContract {

    interface ItemListPresenter : BasePresenter {
        fun loadInitData()
        fun loadMoreData()
    }

    interface ItemListView : BaseView {

        fun loadInitDataSuccess(bean : HomeItemBean)
        fun loadMoreDataSuccess(bean : HomeItemBean)
        fun loadDataFailed(msg : String?)
    }
}