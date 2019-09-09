package com.ryan.common.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * 描述: 全局工具类
 */
object AppUtils {

    val TAG: String = AppUtils::class.java.simpleName

    fun dp2px(dp: Float, mContext: Context): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun addFragment2Activity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        fragmentManager.beginTransaction().add(frameId, fragment).commit()
    }

    /**
     * 隐藏软键盘。
     */
    fun hideSoftKeyboard(activity: Activity) {
        try {
            val view = activity.currentFocus
            if (view != null) {
                val binder = view.windowToken
                val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            LogUtils.e(TAG, e.message ?: "")
        }

    }

    /**
     * 显示软键盘。
     */
    fun showSoftKeyboard(activity: Activity, editText: EditText?) {
        try {
            if (editText != null) {
                editText.requestFocus()
                val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(editText, 0)
            }
        } catch (e: Exception) {
            LogUtils.e(TAG, e.message ?: "")
        }
    }

}

