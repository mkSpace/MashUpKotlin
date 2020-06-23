package com.example.kotlinexample.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinexample.search.Repository
import com.example.kotlinexample.search.RepositoryDao
import dagger.Provides
import javax.inject.Singleton

@Database(
    entities = [Repository::class],
    version = 1
)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun searchDao(): RepositoryDao
}