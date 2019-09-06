package com.ryan.provider.common


import com.alibaba.android.arouter.launcher.ARouter
import com.ryan.common.common.Constant
import com.ryan.common.utils.AppPrefsUtils
import com.ryan.provider.router.RouterPath

/**
 * 顶级函数，判断是否登录
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(Constant.LOGIN_SP_TOKEN).isNotEmpty()
}

/**
 *  如果已经登录，进行传入的方法处理
 *  如果没有登录，进入登录界面
 */
fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.MineModule.LOGIN_PATH).navigation()
    }
}
