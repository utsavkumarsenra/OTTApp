package com.arthlimchiu.basicdaggertutorial.di

import com.arthlimchiu.basicdaggertutorial.ui.view.MainViewModelFactory
import com.arthlimchiu.basicdaggertutorial.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModelFactory(userRepository: UserRepository): MainViewModelFactory {
        return MainViewModelFactory(userRepository)
    }
}