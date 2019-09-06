package com.ryan.test

import dagger.Module
import dagger.Provides
import javax.inject.Named


/**
 * 提供类对象
 */
@Module
class StudentModule {

    //    @Provides
//    fun providesStudent(): Student {
//        return Student()
//    }
    @Provides
    @TestQualifier("test")
    fun providesName(): String {
        return "test!!!"
    }

    @Provides
    @Named("b")
    fun providesLastName(): String {
        return "this is lastName"
    }

    @Provides
    fun providesDefaultName(): String {
        return "this is default!!!"
    }


    @Provides
    fun providesPair(): Pair<String, Int> {
        return "a" to 1
    }
}