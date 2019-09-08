package com.ryan.common.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * 描述: 全局工具类
 */
object AppUtils {

    fun dp2px(dp: Float, mContext: Context): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun addFragment2Activity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int) {
        fragmentManager.beginTransaction().add(frameId, fragment).commit()
    }

}

