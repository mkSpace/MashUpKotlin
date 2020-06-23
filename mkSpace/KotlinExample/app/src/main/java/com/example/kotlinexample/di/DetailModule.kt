package com.example.kotlinexample.di

import com.example.kotlinexample.Constants
import com.example.kotlinexample.detail.DetailFragment
import dagger.Module
import dagger.Provides

@Module
object DetailModule {

    @Provides
    @FragmentScoped
    @UseCase("repositoryId")
    fun provideRepositoryId(fragment: DetailFragment): String =
        fragment.arguments?.getString(Constants.REPOSITORY_ID) ?: ""

    @Provides
    @FragmentScoped
    @UseCase("userName")
    fun provideUserName(fragment: DetailFragment): String =
        fragment.arguments?.getString(Constants.USER_NAME) ?: ""

}