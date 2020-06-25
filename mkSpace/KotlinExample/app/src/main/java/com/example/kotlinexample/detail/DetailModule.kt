package com.example.kotlinexample.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.kotlinexample.Constants
import com.example.kotlinexample.di.StringTypeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object DetailModule {

    @Provides
    @StringTypeUseCase("repositoryId")
    fun provideRepositoryId(fragment: DetailFragment): String =
        fragment.arguments?.getString(Constants.REPOSITORY_ID) ?: ""

    @Provides
    @StringTypeUseCase("userName")
    fun provideUserName(fragment: DetailFragment): String =
        fragment.arguments?.getString(Constants.USER_NAME) ?: ""

    @Provides
    fun provideDetailAdapter(@ActivityContext context: Context): DetailAdapter =
        DetailAdapter { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it))) }
}