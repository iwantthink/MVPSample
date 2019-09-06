package com.ryan.common.injection.module


import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * 提供LifecycleProvider实例的Module
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    /**
     * 创建生命周期实例实例
     */
    @Provides
    fun providesLifecycleProvider():LifecycleProvider<*>{
        return lifecycleProvider
    }
}

