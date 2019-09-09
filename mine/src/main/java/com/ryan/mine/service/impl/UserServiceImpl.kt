package com.ryan.mine.service.impl

import com.ryan.common.ext.convert
import com.ryan.mine.data.entity.UserInfo
import com.ryan.mine.data.repository.UserRepository
import com.ryan.mine.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  对返回的Observable进行处理
 */
class UserServiceImpl @Inject constructor() : UserService {

    /**
     * 依赖注入
     */
    @Inject
    lateinit var repository: UserRepository

    override fun login(username: String, password: String): Observable<UserInfo> {
        return repository.login(username, password).convert()

    }
}