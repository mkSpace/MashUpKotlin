package com.example.kotlinexample.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinexample.BuildConfig
import com.example.kotlinexample.room.SampleDatabase
import com.example.kotlinexample.search.RepositoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): SampleDatabase =
        Room.databaseBuilder(context, SampleDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideRepositoryDao(db: SampleDatabase): RepositoryDao = db.searchDao()
}