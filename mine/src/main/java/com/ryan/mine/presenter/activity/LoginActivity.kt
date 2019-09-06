package com.ryan.mine.presenter.activity


import android.graphics.Paint
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ryan.common.common.AppManager
import com.ryan.common.ext.onClick
import com.ryan.common.presenter.activity.AppMvpActivity
import com.ryan.common.utils.AlerterUtils
import com.ryan.mine.R
import com.ryan.mine.data.entity.UserInfo
import com.ryan.mine.injection.component.DaggerUserComponent
import com.ryan.mine.injection.module.UserModule
import com.ryan.mine.presenter.LoginPresenter
import com.ryan.mine.presenter.view.LoginView
import com.ryan.mine.utils.UserPrefsUtils
import com.ryan.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.input_login_layout.*
import org.jetbrains.anko.toast

@Route(path = RouterPath.MineModule.LOGIN_PATH)
class LoginActivity : AppMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    /**
     * 初始化数据，继承自父类
     */
    override fun initData() {

    }

    /**
     * 初始化视图，继承自父类
     */
    override fun initView() {
        mLoginLayout.setBackgroundResource(R.drawable.login_bg)
//        mForgetPwdBtn = find(R.id.mForgetPwdBtn)
        mForgetPwdBtn!!.paint.isAntiAlias = true //去掉锯齿
        mForgetPwdBtn!!.paint.flags = Paint.UNDERLINE_TEXT_FLAG //添加下划线
        //登录
        mLoginBtn.onClick(this)
        //注册
//        mTitleLayout.getSignInBtn().onClick(this)
        //忘记密码
        mForgetPwdBtn!!.onClick(this)

    }

    /**
     * 重写点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
//            R.id.mSignInBtn -> {
//
//            }
            R.id.mForgetPwdBtn -> {
                toast("忘记密码")
            }
            R.id.mLoginBtn -> {
                if (mUserNameEt.text.toString() == "" || mPwdEt.text.toString() == "") {
                    AlerterUtils.error(this, "消息通知", "用户名或密码不能为空！")
                    return
                }
                /**
                 * 登录方法
                 */
                mPresenter.login(mUserNameEt.text.toString(), mPwdEt.text.toString())
            }
        }
    }

    /**
     * 登录回调，保存用户信息，返回首页，实现LoginView
     */
    override fun loginResult(result: UserInfo) {
        toast("登录成功")
        //保存用户信息
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

    /**
     * 完成具体的Dagger注入(实现成员变量mPresenter，以及其内部变量注入)
     */
    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(baseActivityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    /**
     * 绑定布局文件，继承自父类
     */
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    /**
     * 点击back键关闭页面
     */
    override fun onBackPressed() {
        AppManager.instance.finishActivity(this)
    }
}