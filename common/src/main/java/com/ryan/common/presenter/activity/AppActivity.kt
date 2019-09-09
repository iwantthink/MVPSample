package com.ryan.common.presenter.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.ryan.common.R
import com.ryan.common.common.AppManager
import com.ryan.common.common.callback.PermissionListener
import com.ryan.common.eventbus.event.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

/**
 * Activity基类
 * 封装了设置布局，初始化数据，初始化View
 */
abstract class AppActivity : AppCompatActivity() {

    /**
     * 当前Activity是否在前台
     */
    protected var isActive: Boolean = false

    @Inject
    lateinit var mActivity: Activity

    /**
     * 权限请求回调
     */
    private var mListener: PermissionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        // 设置状态栏透明
        StatusBarUtil.setTranslucent(this, 39)
        setContentView(layoutId())
        EventBus.getDefault().register(this)
        initView()
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    override fun onResume() {
        super.onResume()
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        isActive = false
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
        EventBus.getDefault().unregister(this)
    }

    /**
     * EventBus处理的基础方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }

    /**
     * 设置ToolBar
     */
    protected fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        // 这里获取到的其实就是 toolBar
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * 检查和处理运行时权限，并将用户授权的结果通过PermissionListener进行回调。
     *
     * @param permissions
     * 要检查和处理的运行时权限数组
     * @param listener
     * 用于接收授权结果的监听器
     */
    protected fun handlePermissions(permissions: Array<String>?, listener: PermissionListener) {
        if (permissions == null || mActivity == null) {
            return
        }
        mListener = listener
        val requestPermissionList = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(mActivity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionList.add(permission)
            }
        }
        if (requestPermissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(mActivity!!, requestPermissionList.toTypedArray(), 1)
        } else {
            listener.onGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty()) {
                val deniedPermissions = ArrayList<String>()

                // 找出授权失败的权限
                for (i in grantResults.indices) {
                    val grantResult = grantResults[i]
                    val permission = permissions[i]
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        deniedPermissions.add(permission)
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    mListener!!.onGranted()
                } else {
                    mListener!!.onDenied(deniedPermissions)
                }
            }
            else -> {
            }
        }
    }

    /**
     * 设置布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * Activity中由于服务器异常导致加载失败显示的布局。
     */
    private var loadErrorView: View? = null

    /**
     * Activity中由于网络异常导致加载失败显示的布局。
     */
    private var badNetworkView: View? = null

    /**
     * Activity中当界面上没有任何内容时展示的布局。
     */
    private var noContentView: View? = null

    /**
     * 当Activity中的加载内容服务器返回失败，通过此方法显示提示界面给用户。
     *
     * 展示错误的控件是懒加载的
     * @param tip 界面中的提示信息
     */
    protected fun showLoadErrorView(tip: String) {
        if (loadErrorView != null) {
            loadErrorView?.visibility = View.VISIBLE
            return
        }
        val viewStub = findViewById<ViewStub>(R.id.loadErrorView)
        if (viewStub != null) {
            loadErrorView = viewStub.inflate()
            val loadErrorText = loadErrorView?.findViewById<TextView>(R.id.loadErrorText)
            loadErrorText?.text = tip
        }
    }

    /**
     * 当Activity中的内容因为网络原因无法显示的时候，通过此方法显示提示界面给用户。
     *
     * @param listener 重新加载点击事件回调
     */
    protected fun showBadNetworkView(listener: View.OnClickListener) {
        if (badNetworkView != null) {
            badNetworkView?.visibility = View.VISIBLE
            return
        }
        val viewStub = findViewById<ViewStub>(R.id.badNetworkView)
        if (viewStub != null) {
            badNetworkView = viewStub.inflate()
            val badNetworkRootView = badNetworkView?.findViewById<View>(R.id.badNetworkRootView)
            badNetworkRootView?.setOnClickListener(listener)
        }
    }

    /**
     * 当Activity中没有任何内容的时候，通过此方法显示提示界面给用户。
     * @param tip
     * 界面中的提示信息
     */
    protected fun showNoContentView(tip: String) {
        if (noContentView != null) {
            noContentView?.visibility = View.VISIBLE
            return
        }
        val viewStub = findViewById<ViewStub>(R.id.noContentView)
        if (viewStub != null) {
            noContentView = viewStub.inflate()
            val noContentText = noContentView?.findViewById<TextView>(R.id.noContentText)
            noContentText?.text = tip
        }
    }

    /**
     * 将load error view进行隐藏。
     */
    protected fun hideLoadErrorView() {
        loadErrorView?.visibility = View.GONE
    }

    /**
     * 将no content view进行隐藏。
     */
    protected fun hideNoContentView() {
        noContentView?.visibility = View.GONE
    }

    /**
     * 将bad network view进行隐藏。
     */
    protected fun hideBadNetworkView() {
        badNetworkView?.visibility = View.GONE
    }

    /**
     * Loading
     */
    private var mLoadingDialog: ProgressDialog? = null

    /**
     * 展示进度条
     */
    fun showProgressDialog(title: String?, message: String?) {
        if (mLoadingDialog == null) {
            mLoadingDialog = ProgressDialog(this).apply {
                if (title != null) {
                    setTitle(title)
                }
                if (message != null) {
                    setMessage(message)
                }
                setCancelable(true)
            }
        }
        mLoadingDialog?.show()
    }

    /**
     * 隐藏进度条
     */
    fun closeProgressDialog() {
        mLoadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

}