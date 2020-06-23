package com.example.kotlinexample.di

import com.example.kotlinexample.api.UserService
import com.example.kotlinexample.user.UserRemoteDataSource
import com.example.kotlinexample.user.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(service: UserService) = UserRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideUserRepository(remote: UserRemoteDataSource) = UserRepository(remote)
}