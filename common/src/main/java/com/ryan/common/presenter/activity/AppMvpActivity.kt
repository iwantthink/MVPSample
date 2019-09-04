package com.ryan.common.presenter.activity

import android.os.Bundle
import com.ryan.common.App
import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.component.DaggerActivityComponent
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.injection.module.LifecycleProviderModule
import com.ryan.common.presenter.AppPresenter
import com.ryan.common.presenter.view.AppView
import javax.inject.Inject

abstract class AppMvpActivity<P : AppPresenter<*>> : AppActivity(), AppView {

    /**
     * acitviy 持有presenter
     */
    @Inject
    lateinit var mPresenter: P

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initFragmentInjection()
        injectComponent()
    }

    /**
     * 显示加载框的默认实现
     */
    override fun showLoading() {

    }

    /**
     * 隐藏加载框的默认实现
     */
    override fun hideLoading() {

    }

    /**
     * 错误信息的默认实现
     */
    override fun onError(message: String) {

    }

    /**
     * 依赖注解子类强制实现
     */
    abstract fun injectComponent()

    /**
     * 初始化依赖注解
     */
    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as App).appComponent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }


}