package com.ryan.mine.presenter

import com.ryan.common.presenter.AppPresenter
import com.ryan.mine.presenter.view.LoginView
import com.zyao89.view.zloading.Z_TYPE
import javax.inject.Inject


/**
 *
 * 登录模块的Presenter
 *
 * 1. 构造函数被inject修饰,直接提供构工厂类(生成LoginPresenter)
 * 2. 必须使用Inject提供实例，这样父类中的需要注入的变量才能够成功注入(如果使用Module，就必须手动传值，而不是自动注入)
 *
 */
class LoginPresenter @Inject constructor() : AppPresenter<LoginView>() {

    /**
     * 登录功能
     */
    fun login(username: String, password: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading(Z_TYPE.SNAKE_CIRCLE)

    }
}