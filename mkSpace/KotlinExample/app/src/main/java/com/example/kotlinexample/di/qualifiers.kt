package com.example.kotlinexample.di

import javax.inject.Qualifier

@Qualifier
annotation class StringTypeUseCase(val value: String)