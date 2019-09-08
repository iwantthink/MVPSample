package com.ryan.mine.presenter.view


import com.ryan.common.presenter.BaseContract
import com.ryan.mine.data.entity.UserInfo

interface LoginView : BaseContract.BaseView {
    /**
     * 登录回调
     */
    fun loginResult(result: UserInfo)
}