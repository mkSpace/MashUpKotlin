package com.example.kotlinexample.di

import com.example.kotlinexample.detail.DetailFragment
import com.example.kotlinexample.main.MainFragment
import com.example.kotlinexample.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearchFragment(): SearchFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun bindMainFragment(): MainFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [DetailModule::class, DetailViewModelModule::class])
    abstract fun bindDetailFragment(): DetailFragment
}