package com.ryan.test.dog

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PigModule {

    @Provides
    @Singleton
    fun prividePig(): Pig {
        return Pig()
    }
}