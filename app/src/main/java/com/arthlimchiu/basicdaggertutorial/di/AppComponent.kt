package com.arthlimchiu.basicdaggertutorial.di

import com.arthlimchiu.basicdaggertutorial.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}