package com.ryan.test

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TestQualifier(val value: String = "")