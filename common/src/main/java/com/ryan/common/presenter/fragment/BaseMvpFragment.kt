package com.ryan.common.presenter.fragment

import android.os.Bundle
import android.view.View
import com.ryan.common.App
import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.component.DaggerActivityComponent
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.presenter.BaseContract
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
    override fun startLoading() {
        mLoading?.visibility = View.VISIBLE
        hideBadNetworkView()
        hideLoadErrorView()
        hideNoContentView()
    }

    /**
     * 显示加载框的默认实现
     */
    override fun loadFinished() {
        mLoading?.visibility = View.GONE
    }

    /**
     * 显示加载框的默认实现
     */
    override fun loadFailed(message: String) {
        mLoading?.visibility = View.GONE
    }


    abstract fun injectComponent()

    private fun initFragmentInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((activity?.application as App).mAppComponent)
            .activityModule(ActivityModule(this.activity!!))
            .build()
    }
}