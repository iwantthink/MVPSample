package com.ryan.common.presenter.fragment

import android.os.Bundle
import com.ryan.common.App
import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.component.DaggerActivityComponent
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.injection.module.LifecycleProviderModule
import com.ryan.common.presenter.BaseContract
import com.zyao89.view.zloading.Z_TYPE
import javax.inject.Inject

/**
 * MVP中的Fragment基类
 */
abstract class BaseMvpFragment<T : BaseContract.BasePresenter<*>> : AppFragment(), BaseContract.BaseView {

    /**
     * Fragment 持有presenter
     */
    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragmentInjection()
        injectComponent()
    }

    /**
     * 显示加载框的默认实现
     */
    override fun showLoading(type: Z_TYPE) {

    }

    /**
     * 显示加载框的默认实现
     */
    override fun hideLoading() {

    }

    /**
     * 显示加载框的默认实现
     */
    override fun onError(message: String) {

    }


    abstract fun injectComponent()

    private fun initFragmentInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((activity?.application as App).mAppComponent)
            .activityModule(ActivityModule(this.activity!!))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }
}