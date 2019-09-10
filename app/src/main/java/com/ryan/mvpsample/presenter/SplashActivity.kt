package com.ryan.mvpsample.presenter

import android.Manifest
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.ryan.common.common.callback.PermissionListener
import com.ryan.common.presenter.activity.BaseMvpActivity
import com.ryan.mvpsample.R
import com.ryan.mvpsample.injection.component.DaggerSplashComponent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class SplashActivity : BaseMvpActivity<SplashContract.SplashPresenter>(), SplashContract.SplashView,
    PermissionListener {
    override fun onGranted() {
        toast("获取权限成功")
    }

    /**
     * TODO 判断某些必要的权限,如果没有那么无法进入App
     */
    override fun onDenied(deniedPermissions: List<String>) {
        // 在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_PHONE_STATE
            )
        ) {
            // 此处需要引导用户去手动设置
            toast("跳转至具体应用的设置页面去设置")
        } else {
            AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("此功能需要权限，否则无法正常使用")
                .setPositiveButton("设置") { _, _: Int ->
                    handlePermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), this)
                }
                .create()
                .show()
        }

    }

    override fun injectComponent() {
        DaggerSplashComponent.builder()
            .activityComponent(mBaseActivityComponent)
            .build()
            .inject(this)

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        mbtnJump.setOnClickListener {

            //            afterLogin {
//                toast("没有登录，前去登录页")
//            }
        }
        handlePermissions(
            arrayOf(Manifest.permission.READ_PHONE_STATE),
            this
        )
    }

}
