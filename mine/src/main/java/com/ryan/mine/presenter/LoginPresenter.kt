package com.ryan.mine.presenter


import com.ryan.common.presenter.AppPresenter
import com.ryan.mine.presenter.view.LoginView
import com.zyao89.view.zloading.Z_TYPE
import javax.inject.Inject


/**
 *
 * 登录模块的Presenter
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