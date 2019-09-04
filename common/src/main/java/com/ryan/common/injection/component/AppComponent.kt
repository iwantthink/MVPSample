package com.ryan.common.injection.component

import android.content.Context
import com.ryan.common.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * App 全局Component 主要是注入全局context
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context(): Context
}