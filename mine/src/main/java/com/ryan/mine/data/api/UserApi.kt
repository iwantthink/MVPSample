package com.ryan.mine.data.api

import com.ryan.common.http.entity.BaseEntity
import com.ryan.mine.data.entity.LoginRequest
import com.ryan.mine.data.entity.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *  用户相关 接口
 */
interface UserApi {

    /**
     *  用户登录
     */
    @POST("userCenter/login")
    fun login(@Body req: LoginRequest): Observable<BaseEntity<UserInfo>>

}