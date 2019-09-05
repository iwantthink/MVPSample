package com.ryan.mine.presenter.view


import com.ryan.common.presenter.view.AppView
import com.ryan.mine.data.entity.UserInfo

interface LoginView : AppView {
    /**
     * 登录回调
     */
    fun loginResult(result: UserInfo)
}