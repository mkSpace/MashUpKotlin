package com.example.kotlinexample.di

import com.example.kotlinexample.BaseSchedulerProvider
import com.example.kotlinexample.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SchedulerProviderModule {
    @Provides
    @Singleton
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider
}