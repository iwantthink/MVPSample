package com.ryan.common.presenter

import android.content.Context
import com.ryan.common.presenter.view.AppView
import com.ryan.common.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Present的基础类
 */
open class AppPresenter<V : AppView> {

    lateinit var mView: V

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

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