package com.ryan.mine.injection.component

import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.scope.PreComponentScope
import com.ryan.mine.injection.module.UserModule
import com.ryan.mine.presenter.activity.LoginActivity
import dagger.Component

@PreComponentScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [UserModule::class]
)
interface UserComponent {
    fun inject(activity: LoginActivity)
}