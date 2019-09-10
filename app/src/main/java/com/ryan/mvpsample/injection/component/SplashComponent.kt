package com.ryan.mvpsample.injection.component

import com.ryan.common.injection.component.ActivityComponent
import com.ryan.common.injection.scope.PreComponentScope
import com.ryan.mvpsample.presenter.SplashActivity
import dagger.Component

@PreComponentScope
@Component(dependencies = [ActivityComponent::class])
interface SplashComponent {

    fun inject(activity: SplashActivity)
}