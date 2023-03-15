package com.arthlimchiu.basicdaggertutorial.di

import com.arthlimchiu.basicdaggertutorial.Api
import com.arthlimchiu.basicdaggertutorial.UserRepository
import com.arthlimchiu.basicdaggertutorial.UserRepositoryImpl
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