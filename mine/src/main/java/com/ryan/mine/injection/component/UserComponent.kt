package com.ryan.mine.injection.component

import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.scope.PreComponentScope
import com.ryan.mine.injection.module.UserModule
import com.ryan.mine.presenter.activity.LoginActivity
import dagger.Component

/**
 * 集成了 ActivityComponent
 *
 * 1. 可以用来注入UserModule的内容
 *
 * 2. 可以借助ActivityComponent注入
 */
@PreComponentScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [UserModule::class]
)
interface UserComponent {
    fun inject(activity: LoginActivity)
}