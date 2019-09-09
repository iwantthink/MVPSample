package com.ryan.mine.data.api

import com.ryan.common.http.entity.BaseEntity
import com.ryan.mine.data.entity.LoginRequest
import com.ryan.mine.data.entity.UserInfo
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *  用户相关接口
 *  1. 描述网络请求参数
 *  2. 提供方法
 */
interface UserApi {

    /**
     *  用户登录
     */
    @POST("userCenter/login")
    fun login(@Body req: LoginRequest): Observable<BaseEntity<UserInfo>>

}