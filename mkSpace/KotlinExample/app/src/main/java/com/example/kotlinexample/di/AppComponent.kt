package com.example.kotlinexample.di

import android.app.Application
import com.example.kotlinexample.KotlinExampleApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RemoteModule::class,
        RoomModule::class,
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class,
        SearchRepositoryModule::class,
        UserRepositoryModule::class,
        SchedulerProviderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<KotlinExampleApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun remoteModule(remoteModule: RemoteModule): Builder

        fun build(): AppComponent
    }
}