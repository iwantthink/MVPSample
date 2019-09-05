package com.ryan.mine.injection.module


import com.ryan.mine.service.UserService
import com.ryan.mine.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * 描述: 用户module
 *
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}