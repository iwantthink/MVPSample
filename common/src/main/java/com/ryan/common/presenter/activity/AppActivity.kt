package com.ryan.common.presenter.activity

import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import com.ryan.common.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Activity基类
 * 封装了设置布局，初始化数据，初始化View
 */
abstract class AppActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        // 设置状态栏透明
        StatusBarUtil.setTranslucent(this, 39)
        setContentView(layoutId())
        initView()
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    /**
     * 设置布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

}