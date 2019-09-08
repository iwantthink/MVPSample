package com.ryan.common.rx

import com.ryan.common.presenter.BaseContract
import rx.Subscriber

/**
 * 订阅者通用实现
 */
open class BaseSubscriber<T>(val v: BaseContract.BaseView) : Subscriber<T>() {

    /**
     * 隐藏加载框,向界面反馈错误
     */
    override fun onError(e: Throwable?) {
        v.hideLoading()
        if (e is BaseException) {
            v.onError(e.msg)
        }
    }

    /**
     * 交给子类去实现具体的逻辑
     */
    override fun onNext(t: T) {
    }

    /**
     * 隐藏加载框即可
     */
    override fun onCompleted() {
        v.hideLoading()
    }
}