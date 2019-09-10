package com.ryan.common.presenter.activity

import android.os.Bundle
import android.view.KeyEvent
import com.alibaba.android.arouter.launcher.ARouter
import com.ryan.common.App
import com.ryan.common.R
import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.component.DaggerActivityComponent
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.presenter.BaseContract
import com.tapadoo.alerter.Alerter
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *  MVP中的Activity基类
 */
abstract class BaseMvpActivity<P : BaseContract.BasePresenter<*>> : AppActivity(), BaseContract.BaseView {

    /**
     * Activity 持有presenter
     *
     * 1. 通过具体Activity对应的Component对其进行赋值!!!
     *     (例如LoginActivity :BaseMvpActivity, mPresenter 就由LoginComponent对其进行赋值，
     *       由于LoginPresenter构造函数直接被Inject注解，所以不需要借助Module)
     *
     */
    @Inject
    lateinit var mPresenter: P

    /**
     * Activity对应的基础Component,用来为其子类Component提供实例
     */
    lateinit var mBaseActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        initActivityInjection()
        injectComponent()
        // 注册当前Activity
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unsubscribeDisposable()
        mPresenter.detachView()
    }

    /**
     * 显示加载框的默认实现
     */
    override fun startLoading() {
        showProgressDialog(null, null)
        hideBadNetworkView()
        hideLoadErrorView()
        hideNoContentView()
    }

    /**
     * 隐藏加载框的默认实现
     */
    override fun loadFinished() {
        closeProgressDialog()
    }

    /**
     * 错误信息的默认实现
     */
    override fun loadFailed(message: String) {
        closeProgressDialog()
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
        mBaseActivityComponent = DaggerActivityComponent.builder()
            .appComponent((application as App).mAppComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                toast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}