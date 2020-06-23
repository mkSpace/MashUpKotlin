package com.example.kotlinexample.di

import com.example.kotlinexample.main.MainActivity
import com.example.kotlinexample.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}
