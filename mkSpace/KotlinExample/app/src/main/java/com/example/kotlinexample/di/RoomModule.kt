package com.example.kotlinexample.di

import android.content.Context
import com.example.kotlinexample.room.SampleDatabase
import com.example.kotlinexample.search.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): SampleDatabase =
        SampleDatabase.getInstance(context)

    @Provides
    fun provideRepositoryDao(database: SampleDatabase): RepositoryDao = database.getRepositoryDao()
}