package com.ryan.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.ryan.common.injection.component.AppComponent
import com.ryan.common.injection.component.DaggerAppComponent
import com.ryan.common.injection.module.AppModule

/**
 * 全局Application
 */
class App : Application() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInJection()
        sContext = this
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

    /**
     * 初始化AppComponent
     */
    private fun initAppInJection() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    /**
     * 全局伴生对象
     */
    companion object {
        lateinit var sContext: Context
    }
}