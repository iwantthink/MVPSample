package com.ryan.test.str

import com.ryan.test.TestQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class StringModule {

    @Provides
    @TestQualifier("test")
    fun provideTestStr(): String {
        return "test test test!!"
    }


    @Provides
    @Named("lastName")
    fun provideLastName(): String {
        return "lastname lastname"
    }

    @Provides
    fun providesDefaultStr(): String {
        return "default default!!!"
    }
}