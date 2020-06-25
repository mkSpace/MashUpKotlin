package com.example.kotlinexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//{@literal @}HiltAndroidApp(Application.class)
// *   public final class FooApplication extends Hilt_FooApplication {
@HiltAndroidApp
class KotlinExampleApplication : Application()