package com.arthlimchiu.basicdaggertutorial

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .build()
    }
}

lateinit var component: AppComponent