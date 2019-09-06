package com.ryan.common.presenter

import android.content.Context
import com.ryan.common.presenter.view.AppView
import com.ryan.common.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * MVP中Present的基础类
 *
 * 1. 基础类中的依赖注入，会交给子类对应的Component实现
 *
 */
open class AppPresenter<V : AppView> {

    /**
     * 持有View层
     */
    lateinit var mView: V

    /**
     * 依赖注入
     */
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    /**
     * 依赖注入
     */
    @Inject
    lateinit var context: Context

    /**
     *  检查网络是否可用
     */
    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(context)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}