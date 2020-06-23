package com.example.kotlinexample.di

import com.example.kotlinexample.api.SearchService
import com.example.kotlinexample.search.RepositoryDao
import com.example.kotlinexample.search.SearchRemoteDataSource
import com.example.kotlinexample.search.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SearchRepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRemoteDataSource(service: SearchService) = SearchRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideSearchRepository(remoteDataSource: SearchRemoteDataSource, dao: RepositoryDao) =
        SearchRepository(remoteDataSource, dao)
}