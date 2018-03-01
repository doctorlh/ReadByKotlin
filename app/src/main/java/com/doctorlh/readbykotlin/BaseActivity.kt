package com.doctorlh.readbykotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

/**
 * Created by Administrator on 2018/2/27 0027.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimary))
        setContentView(getLayoutId())
        initView()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView()
}