package com.ryan.test

import com.ryan.test.dog.DogModule
import com.ryan.test.dog.PigModule
import com.ryan.test.str.StringModule
import dagger.Component
import javax.inject.Singleton

/**
 * 组件(注射器)
 *
 * 1. 将生成一个Injector ,用来关联变量 和 构造函数(或Module)
 */
@Singleton
@Component(
    modules = [StudentModule::class,
        StringModule::class,
        DogModule::class,
        PigModule::class]
)
interface TestComponent {

    fun inject(activity: TestActivity)
}