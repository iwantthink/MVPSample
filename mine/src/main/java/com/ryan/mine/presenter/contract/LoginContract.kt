package com.ryan.mine.presenter.contract

import com.ryan.common.ext.log
import com.ryan.common.presenter.BaseContract
import com.ryan.mine.service.impl.UserServiceImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginContract {


    interface LoginView : BaseContract.BaseView {
        /**
         * 登录回调
         */
        fun loginResult()
    }

    /**
     *
     * 登录模块的Presenter
     *
     * 1. 构造函数被inject修饰,直接提供构工厂类(生成LoginPresenter)
     * 2. 必须使用Inject提供实例，这样父类中的需要注入的变量才能够成功注入(如果使用Module，就必须手动传值，而不是自动注入)
     *
     */
    class LoginPresenter @Inject constructor() : BaseContract.BasePresenter<LoginView>() {

        @Inject
        lateinit var userServiceImpl: UserServiceImpl

//        @Inject
//        lateinit var mActivity: Activity

        /**
         * 登录
         */
        fun login(username: String, password: String) {
            if (!checkNetWork()) {
                return
            }
            mView?.startLoading()
            /**
             * 失败时 onError中会带有失败的code 和message
             *
             * 成功时 onNext中仅有一个成功的UserInfo
             */
//            userServiceImpl.login(username, password)
//                .execute(object : BaseSubscriber<UserInfo>(mView, mCompositeSubscription) {
//
//                    override fun onNext(t: UserInfo) {
//                        super.onNext(t)
//                        log<LoginContract>("onNext触发 t = $t")
//
//                    }
//
//                    override fun onComplete() {
//                        super.onComplete()
//                        log<LoginContract>("onComplete触发")
//                        mView?.loadFinished()
//                        mView?.loginResult(UserInfo("", "", "", "", "", ""))
//
//                    }
//
//                    override fun onError(e: Throwable) {
//                        super.onError(e)
//                        log<LoginContract>("onError触发 e = $e")
//
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        super.onSubscribe(d)
//                        log<LoginContract>("onSubscribe触发")
//                    }
//
//                })

            val disposable = Observable.just("1").delay(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    log<LoginContract>("it = $it")
                    mView?.loginResult() ?: log<LoginContract>("mView被销毁")
                }
//            subscribeDisposable(disposable)

        }
    }
}