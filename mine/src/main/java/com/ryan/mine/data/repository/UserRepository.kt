package com.ryan.mine.data.repository

import com.ryan.common.http.HttpHelper
import com.ryan.common.http.entity.BaseEntity
import com.ryan.mine.data.api.UserApi
import com.ryan.mine.data.entity.LoginRequest
import com.ryan.mine.data.entity.UserInfo
import rx.Observable
import javax.inject.Inject

/**
 *
 */
class UserRepository @Inject constructor() {

    /*
        用户登录
     */
    fun login(mobile: String, pwd: String): Observable<BaseEntity<UserInfo>> {
        return HttpHelper.instance.create(UserApi::class.java).login(LoginRequest(mobile, pwd))
    }

}