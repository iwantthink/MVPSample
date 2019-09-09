package com.ryan.common.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.ryan.common.http.entity.BaseEntity
import com.ryan.common.rx.BaseConvert
import com.ryan.common.rx.BaseConvertBool
import com.ryan.common.rx.BaseSubscriber
import com.ryan.common.utils.GlideUtils
import com.ryan.common.widgets.DefaultTextWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

/**
 * 扩展点击事件
 */
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

/**
 * 函数式扩展
 */
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

/**
 * 扩展页面跳转
 */
inline fun <reified T> Activity.start() {
    this.startActivity(Intent(this, T::class.java))
}

/**
 * activity 制定
 */
inline fun <reified activity : Activity> Activity.startTop() {
    this.startActivity(intentFor<activity>().singleTop().clearTop())
}

/**
 * RxJava:通用数据转换
 */
fun <T> Observable<BaseEntity<T>>.convert(): Observable<T> {
    return flatMap(BaseConvert())
}

/**
 * RxJava:bool值转换
 */
fun <T> Observable<BaseEntity<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseConvertBool())
}

/**
 * RxJava:扩展Observable执行
 *
 * 1. 设置被观察者执行在IO线程,观察者执行在UI线程
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>) {
    //监听主线程
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}

/**
 * 获取颜色
 */
infix fun Context.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)


/**
 * 扩展Button可用性
 */
fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

/**
 * ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/**
 * 扩展视图可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

/**
 * 扩展日志输出
 */
inline fun <reified T> Any?.log(msg: String) {
    Log.e(T::class.java.simpleName, msg)
}
