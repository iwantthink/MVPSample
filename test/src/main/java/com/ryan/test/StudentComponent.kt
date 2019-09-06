package com.ryan.test

import dagger.Component

/**
 * 组件(注射器)
 *
 * 1. 将生成一个Injector ,用来关联变量 和 构造函数(或Module)
 */
@Component(modules = [StudentModule::class])
interface StudentComponent {

    fun inject(activity: TestActivity)
}