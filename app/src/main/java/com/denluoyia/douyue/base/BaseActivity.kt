package com.denluoyia.douyue.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by denluoyia
 * Date 2018/07/20
 * DouYue
 */
abstract class BaseActivity : AppCompatActivity(){

    abstract fun setContentViewId() : Int

    abstract fun doBusiness()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        var contentViewId : Int = setContentViewId()
        if (contentViewId == 0){
            throw IllegalArgumentException("must set activity's layout contentView!")
        }
        setContentView(contentViewId)
        this.doBusiness()
    }

}