package com.ryan.common.injection.component

import android.app.Activity
import android.content.Context
import com.ryan.common.injection.module.ActivityModule
import com.ryan.common.injection.scope.ActivityScope
import dagger.Component

/**
 * Activity级别的Component
 * 提供依赖(@Inject,@Module&@Provides)和需要依赖(@Inject)之间的桥梁
 *
 * 1. 通过context()方法对外提供由AppComponent提供的context
 * 2. 通过activity()方法对外提供由ActivityModule提供的Activity
 * 3. 通过lifecycleProvider()方法对外提供由LifecycleProviderModule提供的LifecycleProvider
 */
@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    /**
     * Component管理Inject标注的构造函数的Activity
     */
    fun activity(): Activity

    /**
     * Component管理Inject标注的构造函数的Context
     * (这个context由AppComponent实现,必须暴露出来,否则会被当做私有)
     */
    fun context(): Context
}