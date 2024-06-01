package com.denluoyia.douyuekotlin.base

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by denluoyia
 * Date 2018/07/20
 * DouYue
 */
abstract class BaseActivity : AppCompatActivity(){

    private lateinit var unBinder : Unbinder

    abstract fun setContentViewId() : Int

    abstract fun doBusiness()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentViewId : Int = setContentViewId()
        if (contentViewId == 0){
            throw IllegalArgumentException("must set activity's layout contentView!")
        }
        setContentView(contentViewId)
        unBinder = ButterKnife.bind(this)
        this.doBusiness()
    }

    override fun onDestroy() {
        unBinder.unbind()
        super.onDestroy()
    }

    fun initToolbar(toolbar : Toolbar){
        setSupportActionBar(toolbar)
        var actionBar :ActionBar? = supportActionBar
        actionBar!!.title = ""
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener({
            onBackPressed()
        })
    }

}