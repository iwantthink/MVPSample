package com.ryan.mine.service.impl

import com.ryan.common.ext.convert
import com.ryan.mine.data.entity.UserInfo
import com.ryan.mine.data.repository.UserRepository
import com.ryan.mine.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 */
class UserServiceImpl @Inject constructor() : UserService {


    @Inject
    lateinit var repository: UserRepository

    override fun login(username: String, password: String): Observable<UserInfo> {
        return repository.login(username, password).convert()

    }
}