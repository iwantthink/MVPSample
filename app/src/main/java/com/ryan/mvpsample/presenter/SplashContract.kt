package com.ryan.mvpsample.presenter

import com.ryan.common.presenter.BaseContract
import javax.inject.Inject

class SplashContract {

    class SplashPresenter @Inject constructor() : BaseContract.BasePresenter<SplashView>() {

    }

    interface SplashView : BaseContract.BaseView {

    }

}