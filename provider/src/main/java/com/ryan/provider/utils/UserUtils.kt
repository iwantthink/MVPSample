package com.ryan.provider.utils

import android.view.View
import android.widget.Toolbar

/**
 *
 */
object UserUtils {

    fun loadUser(header: View, mAppToolBar: Toolbar) {
        mAppToolBar.title = "这是测试的toolbar"
//        var avatar = header.find<CircleImageView>(R.id.mAvatar)
//        var username = header.find<TextView>(R.id.mUsername)
//        var mail = header.find<TextView>(R.id.mMail)
//        val userAvatar = AppPrefsUtils.getString(UserConstant.KEY_SP_USER_ICON)
//        if (userAvatar.isNotEmpty()) {
//            avatar.loadUrl(userAvatar)
//        }
//        username.text = "hello"
//        mail.text = "xxx@gmail.com"
//        mAppToolBar.setAvatarOnInternet(userAvatar)
    }
}