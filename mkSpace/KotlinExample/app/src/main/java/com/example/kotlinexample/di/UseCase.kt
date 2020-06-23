package com.example.kotlinexample.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UseCase(val value: String)
