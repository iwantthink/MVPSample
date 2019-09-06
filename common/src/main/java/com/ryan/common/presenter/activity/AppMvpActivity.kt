package com.ryan.common.presenter.activity

import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.ryan.common.App
import com.ryan.common.R
import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.component.DaggerActivityComponent
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.injection.module.LifecycleProviderModule
import com.ryan.common.presenter.AppPresenter
import com.ryan.common.presenter.view.AppView
import com.tapadoo.alerter.Alerter
import com.zyao89.view.zloading.ZLoadingDialog
import com.zyao89.view.zloading.Z_TYPE
import javax.inject.Inject

/**
 *  MVP中的Activity基类
 */
abstract class AppMvpActivity<P : AppPresenter<*>> : AppActivity(), AppView {

    /**
     * Activity 持有presenter
     *
     * 1. 通过具体Activity对应的Component对其进行赋值!!!
     *     (例如LoginActivity :AppMvpActivity, mPresenter 就由LoginComponent对其进行赋值，
     *       由于LoginPresenter构造函数直接被Inject注解，所以不需要借助Module)
     *
     */
    @Inject
    lateinit var mPresenter: P

    /**
     * Activity对应的Component
     */
    lateinit var baseActivityComponent: ActivityComponent

    lateinit var dialog: ZLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化ZLoadingDialog
        dialog = ZLoadingDialog(this)
        initActivityInjection()
        injectComponent()
        // 注册当前Activity
        ARouter.getInstance().inject(this)
    }

    /**
     * 显示加载框的默认实现
     */
    override fun showLoading(type: Z_TYPE) {
        dialog.setLoadingBuilder(type)//设置类型
            .setLoadingColor(Color.BLACK)//颜色
            .setHintText("Loading...")
            .setHintTextColor(Color.GRAY)  // 设置字体颜色
            .setDurationTime(1.0) // 设置动画时间百分比 - 0.5倍
            .setCanceledOnTouchOutside(false)
            .show()
    }

    /**
     * 隐藏加载框的默认实现
     */
    override fun hideLoading() {
        dialog.cancel()
    }

    /**
     * 错误信息的默认实现
     */
    override fun onError(message: String) {
        Alerter.create(this)
            .setTitle("消息通知")
            .setText(message)
            .enableSwipeToDismiss()
            .setDuration(10000)
            .setBackgroundColorRes(R.color.alert_error_bg)
            .setIcon(R.drawable.alert_error_icon)
            .show()
    }

    /**
     * 子类实现真正的桥梁(Component)
     */
    abstract fun injectComponent()

    /**
     * 创建通用的Activity级别桥梁(Component)
     * 具体的注入操作交给子类Activity中的具体Component
     */
    private fun initActivityInjection() {
        baseActivityComponent = DaggerActivityComponent.builder()
            .appComponent((application as App).mAppComponent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

}