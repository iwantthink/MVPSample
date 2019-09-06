package com.ryan.common.injection.component

import android.content.Context
import com.ryan.common.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Application级别的Component
 * 提供依赖(@Inject,@Module&@Provides)和需要依赖(@Inject)之间的桥梁
 *
 * 1. 通过context()方法对外提供由AppModule创建的Context实例
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context(): Context
}