package com.ryan.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.ryan.common.injection.component.AppComponent
import com.ryan.common.injection.component.DaggerAppComponent
import com.ryan.common.injection.module.AppModule
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInJection()

        context = this
        ARouter.init(this)

    }

    private fun initAppInJection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    /*
        全局伴生对象
     */
    companion object {
        lateinit var context: Context
    }
}