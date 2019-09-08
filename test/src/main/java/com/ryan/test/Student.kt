package com.ryan.test

import javax.inject.Inject
import javax.inject.Named

/**
 * 生成大众工厂类
 */
class Student @Inject constructor(@Named("clazz") val clazz: String) : Human()