package com.arthlimchiu.basicdaggertutorial

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(api: Api): UserRepository {
        return UserRepositoryImpl(api)
    }
}