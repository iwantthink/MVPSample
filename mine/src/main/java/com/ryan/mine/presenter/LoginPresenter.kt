package com.ryan.mine.presenter

import android.app.Activity
import com.ryan.common.ext.execute
import com.ryan.common.ext.log
import com.ryan.common.presenter.BaseContract
import com.ryan.common.rx.BaseSubscriber
import com.ryan.mine.data.entity.UserInfo
import com.ryan.mine.presenter.view.LoginView
import com.ryan.mine.service.impl.UserServiceImpl
import javax.inject.Inject


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

    @Inject
    lateinit var mActivity: Activity

    /**
     * 登录
     */
    fun login(username: String, password: String) {
        if (!checkNetWork()) {
            return
        }
        mView.startLoading()
        /**
         * 失败时 onError中会带有失败的code 和message
         *
         * 成功时 onNext中仅有一个成功的UserInfo
         */
        userServiceImpl.login(username, password)
            .execute(object : BaseSubscriber<UserInfo>(mView) {

            })


    }
}