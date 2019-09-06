package com.ryan.common.injection.module

import android.content.Context
import com.ryan.common.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 提供Context实例的Module(全局变量)
 *
 * 1. ApplicationContext
 * 2. SP
 * 3. TODO...
 */
@Module
class AppModule(private val context: App) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}