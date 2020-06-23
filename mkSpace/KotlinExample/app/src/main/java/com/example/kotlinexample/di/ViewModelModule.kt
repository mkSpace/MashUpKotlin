package com.example.kotlinexample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinexample.SchedulerProvider
import com.example.kotlinexample.detail.DetailViewModel
import com.example.kotlinexample.main.MainViewModel
import com.example.kotlinexample.search.SearchRepository
import com.example.kotlinexample.search.SearchViewModel
import com.example.kotlinexample.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}