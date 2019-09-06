package com.ryan.common.injection.component

import android.app.Activity
import android.content.Context
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.injection.module.LifecycleProviderModule
import com.ryan.common.injection.scope.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Activity级别Component
 * 帮助被@inject注解标注的变量进行初始化与赋值
 */
@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, LifecycleProviderModule::class]
)
interface ActivityComponent {
    /**
     * Component管理Inject标注的构造函数的Activity
     */
    fun activity(): Activity

    /**
     * Component管理Inject标注的构造函数的Context
     */
    fun context(): Context

    /**
     * Component管理Inject标注的构造函数的生命周期管理器
     */
    fun lifecycleProvider(): LifecycleProvider<*>
}