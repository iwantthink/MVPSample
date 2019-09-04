package com.ryan.common.injection.module

import android.content.Context
import com.ryan.common.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: App) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}