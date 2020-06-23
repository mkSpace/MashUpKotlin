package com.example.kotlinexample.network

interface ApiClient {
    fun <T : Any> createService(`class`: Class<T>): T
}