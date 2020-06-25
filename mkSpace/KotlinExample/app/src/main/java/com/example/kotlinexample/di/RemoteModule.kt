package com.example.kotlinexample.di

import com.example.kotlinexample.BaseSchedulerProvider
import com.example.kotlinexample.SchedulerProvider
import com.example.kotlinexample.api.SearchService
import com.example.kotlinexample.api.UserService
import com.example.kotlinexample.network.SampleRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider

    @Provides
    fun provideSearchService(retrofit: SampleRetrofit): SearchService =
        retrofit.create(SearchService::class)

    @Provides
    fun provideUserService(retrofit: SampleRetrofit): UserService =
        retrofit.create(UserService::class)
}