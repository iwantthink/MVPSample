package com.ryan.test.dog

import dagger.Module
import dagger.Provides

@Module
class DogModule {

    @Provides
    fun providesDog(): Creature {
        return Dog()
    }
}