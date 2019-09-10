package com.ryan.common.rx

import com.ryan.common.presenter.BaseContract
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 订阅者通用实现
 */
open class BaseSubscriber<T>(val v: BaseContract.BaseView?, val compositeSubscription: CompositeDisposable) :
    Observer<T> {

    /**
     * 隐藏加载框,向界面反馈错误
     */
    override fun onError(e: Throwable) {
        v?.loadFinished()
        if (e is BaseException) {
            v?.loadFailed(e.msg)
        }
    }

    /**
     * 隐藏加载框即可
     */
    override fun onComplete() {
        v?.loadFinished()
    }

    /**
     * 订阅
     */
    override fun onSubscribe(d: Disposable) {
        compositeSubscription.add(d)
    }

    /**
     * 交给子类去实现具体的逻辑
     */
    override fun onNext(t: T) {
    }

}