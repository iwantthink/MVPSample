package com.ryan.mine.data.repository

import com.ryan.common.http.HttpHelper
import com.ryan.common.http.entity.BaseEntity
import com.ryan.mine.data.api.UserApi
import com.ryan.mine.data.entity.LoginRequest
import com.ryan.mine.data.entity.UserInfo
import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 */
class UserRepository @Inject constructor() {

    fun login(mobile: String, pwd: String): Observable<BaseEntity<UserInfo>> {
        val userApi = HttpHelper.instance.create<UserApi>()
        return userApi.login(LoginRequest(mobile, pwd))
    }

}