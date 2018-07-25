package com.denluoyia.douyuekotlin.presenter

import com.denluoyia.douyuekotlin.base.BasePresenter
import com.denluoyia.douyuekotlin.base.BaseView
import com.denluoyia.douyuekotlin.model.bean.DetailBean

/**
 * Created by denluoyia
 * Date 2018/07/25
 * DouYue
 */
interface DetailContract {
    interface Presenter : BasePresenter {
        fun loadData(postId : String)
    }

    interface View : BaseView {
        fun loadDataSuccess(bean : DetailBean)
        fun loadDataFailed(msg: String?)
    }
}