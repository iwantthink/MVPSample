package com.ryan.common.presenter.view

import com.zyao89.view.zloading.Z_TYPE

/**
 * MVP中View层的基础模块
 */
interface AppView {

    /**
     * 显示加载框
     */
    fun showLoading(type: Z_TYPE)

    /**
     * 隐藏加载框
     */
    fun hideLoading()

    /**
     * 错误回调
     */
    fun onError(message: String)
}