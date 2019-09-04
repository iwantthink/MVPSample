package com.ryan.common.presenter

import android.content.Context
import com.ryan.common.presenter.view.AppView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class AppPresenter<V : AppView> {

    lateinit var mView: V

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context
}