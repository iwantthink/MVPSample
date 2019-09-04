package com.ryan.common.presenter.view

interface AppView {

    /**
     * 显示加载框
     */
    fun showLoading()

    /**
     * 隐藏加载框
     */
    fun hideLoading()

    /**
     * 错误回调
     */
    fun onError(message: String)
}