package com.ryan.test

import android.app.Activity
import com.ryan.test.activity.SeconedActivity
import dagger.Component


@Component
interface BirdComponent {
    fun inject(activity: SeconedActivity)
}