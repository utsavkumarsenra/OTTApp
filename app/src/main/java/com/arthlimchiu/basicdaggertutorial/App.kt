package com.arthlimchiu.basicdaggertutorial

import android.app.Application
import com.arthlimchiu.basicdaggertutorial.di.AppComponent
import com.arthlimchiu.basicdaggertutorial.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .build()
    }
}

lateinit var component: AppComponent