package com.ryan.common.injection.module


import android.app.Activity
import com.ryan.common.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Activity级别的Module
 */
@Module
class ActivityModule(private val activity: Activity) {

    /**
     * 创建Activity实例
     */
    @Provides
    @ActivityScope
    fun providesActivity(): Activity {
        return activity
    }
}