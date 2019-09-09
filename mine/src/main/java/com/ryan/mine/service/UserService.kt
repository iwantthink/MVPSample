package com.ryan.mine.service

import com.ryan.mine.data.entity.UserInfo
import io.reactivex.Observable

/**
 *  定义请求规范，返回Observable
 */
interface UserService {

    fun login(username: String, password: String): Observable<UserInfo>

}