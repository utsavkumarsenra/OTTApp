package com.arthlimchiu.basicdaggertutorial.di

import com.arthlimchiu.basicdaggertutorial.Api
import com.arthlimchiu.basicdaggertutorial.repository.UserRepository
import com.arthlimchiu.basicdaggertutorial.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}