package com.example.kotlinexample.rx

import android.util.Log

class ErrorLogger(private val message: String = "") : (Throwable) -> Unit {

    override fun invoke(t: Throwable) {
        Log.e(TAG, message, t)
    }

    companion object {
        private const val TAG = "ErrorLogger"
    }
}
