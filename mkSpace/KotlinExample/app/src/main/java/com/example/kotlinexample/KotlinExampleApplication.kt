package com.example.kotlinexample

import com.example.kotlinexample.di.DaggerAppComponent
import com.example.kotlinexample.di.RemoteModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class KotlinExampleApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .remoteModule(RemoteModule(BuildConfig.BASE_URL))
            .build()
    }
}