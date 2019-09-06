package com.ryan.test

import javax.inject.Inject

/**
 * 生成大众工厂类
 */
class Student @Inject constructor(val clazz: String) : com.ryan.test.Human()