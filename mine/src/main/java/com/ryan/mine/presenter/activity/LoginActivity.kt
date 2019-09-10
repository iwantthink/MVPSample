package com.ryan.mine.presenter.activity


import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ryan.common.common.AppManager
import com.ryan.common.ext.log
import com.ryan.common.ext.onClick
import com.ryan.common.presenter.activity.BaseMvpActivity
import com.ryan.common.utils.AlerterUtils
import com.ryan.mine.R
import com.ryan.mine.injection.component.DaggerUserComponent
import com.ryan.mine.injection.module.UserModule
import com.ryan.mine.presenter.contract.LoginContract
import com.ryan.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

@Route(path = RouterPath.MineModule.LOGIN_PATH)
class LoginActivity : BaseMvpActivity<LoginContract.LoginPresenter>(), LoginContract.LoginView, View.OnClickListener {

    /**
     * 绑定布局文件，继承自父类
     */
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    /**
     * 初始化数据，继承自父类
     */
    override fun initData() {
    }

    /**
     * 初始化视图，继承自父类
     */
    override fun initView() {

        mLoginLayout.setBackgroundColor(resources.getColor(R.color.common_blue))
        //登录
        mBtnLogin.onClick(this)
    }

    /**
     * 重写点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {

            R.id.mBtnLogin -> {
                if (mEtMail.text.toString() == "" || mEtPsw.text.toString() == "") {
                    AlerterUtils.error(this, "消息通知", "用户名或密码不能为空！")
                    return
                }
                /**
                 * 登录方法
                 */
                mPresenter.login(mEtMail.text.toString(), mEtPsw.text.toString())
            }
        }
    }

    /**
     * 登录回调，保存用户信息
     */
    override fun loginResult() {
        runOnUiThread {
            toast("登录成功")
        }
        //保存用户信息
//        UserPrefsUtils.putUserInfo(result)
//        finish()
    }

    /**
     * 完成具体的Dagger注入(实现成员变量mPresenter，以及其内部变量注入)
     */
    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(mBaseActivityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)
        mPresenter.attachView(this)
    }

    /**
     * 点击back键关闭页面
     */
    override fun onBackPressed() {
        log<LoginActivity>("关闭所有页面!!!!!")
        AppManager.instance.finishActivity(this)
    }
}