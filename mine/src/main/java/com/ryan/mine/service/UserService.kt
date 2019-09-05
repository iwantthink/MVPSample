package com.ryan.mine.service

import com.ryan.mine.data.entity.UserInfo
import rx.Observable

/**
 * 描述: ${DESCRIPTION}
 * @author Think
 * @create 2018-10-22 9:39
 */
interface UserService {

    fun login(username: String, password: String): Observable<UserInfo>

}