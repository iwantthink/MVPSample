package com.ryan.common.presenter

import android.content.Context
import com.ryan.common.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import com.zyao89.view.zloading.Z_TYPE
import javax.inject.Inject

interface BaseContract {

    /**
     * MVP中Present的基础类
     *
     * 1. 基础类中的依赖注入，会交给子类对应的Component实现
     *
     */
    abstract class BasePresenter<V : BaseView> {

        /**
         * 持有View层
         */
        lateinit var mView: V

        /**
         * Dagger注入LifecycleProvider
         */
        @Inject
        lateinit var mLifecycleProvider: LifecycleProvider<*>

        /**
         * Dagger注入ApplicationContext
         */
        @Inject
        lateinit var mContext: Context

        /**
         *  检查网络是否可用
         */
        fun checkNetWork(): Boolean {
            if (NetWorkUtils.isNetWorkAvailable(mContext)) {
                return true
            }
            mView.onError("网络不可用")
            return false
        }
    }


    /**
     * MVP中View层的基础模块
     */
    interface BaseView {

        /**
         * 显示加载框
         */
        fun showLoading(type: Z_TYPE)

        /**
         * 隐藏加载框
         */
        fun hideLoading()

        /**
         * 错误回调
         */
        fun onError(message: String)
    }
}