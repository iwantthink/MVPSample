package com.ryan.common.rx

/**
 * 全局异常
 */
class BaseException(val status: Int, val msg: String) : Throwable()