package com.example.kotlinexample.di

import com.example.kotlinexample.api.SearchService
import com.example.kotlinexample.api.UserService
import com.example.kotlinexample.network.ApiClient
import com.example.kotlinexample.network.ApiClientImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient = ApiClientImpl(baseUrl)

    @Provides
    @Singleton
    fun provideSearchService(apiClient: ApiClient) =
        apiClient.createService(SearchService::class.java)

    @Provides
    @Singleton
    fun getUserService(apiClient: ApiClient) = apiClient.createService(UserService::class.java)
}