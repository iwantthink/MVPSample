package com.ryan.common.presenter

import android.content.Context
import com.ryan.common.utils.NetWorkUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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
         * View层
         */
        protected var mView: V? = null

        /**
         * Dagger注入ApplicationContext
         */
        @Inject
        lateinit var mContext: Context

        protected var mCompositeSubscription: CompositeDisposable = CompositeDisposable()

        fun subscribeDisposable(disposable: Disposable): Unit {
            mCompositeSubscription.add(disposable)
        }

        fun unsubscribeDisposable(): Unit {
            mCompositeSubscription.clear()
        }

        /**
         * 绑定View
         */
        fun attachView(view: V): Unit {
            mView = view
        }

        /**
         * 解绑View,避免内存泄露
         */
        fun detachView(): Unit {
            mView = null
        }

        /**
         *  检查网络是否可用
         */
        fun checkNetWork(): Boolean {
            if (NetWorkUtils.isNetWorkAvailable()) {
                return true
            }
            mView?.loadFailed("网络不可用")
            return false
        }
    }


    /**
     * MVP中View层的基础模块
     */
    interface BaseView {

        /**
         * 开始加载
         */
        fun startLoading()

        /**
         * 结束加载
         */
        fun loadFinished()

        /**
         * 加载错误
         */
        fun loadFailed(message: String)
    }
}