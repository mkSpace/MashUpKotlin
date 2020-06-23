package com.example.kotlinexample.network

import com.example.kotlinexample.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ApiClientImpl @Inject constructor(baseUrl: String) : ApiClient {

    companion object {
        private const val TIMEOUT_CONNECT = 10L
    }

    private val retrofit: Retrofit

    init {
        val httpClient = createOkHttpClient()
        retrofit = createRetrofitInstance(httpClient, baseUrl)
    }

    private fun createOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
        .addInterceptor(createLoggingInterceptor())
        .build()

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun createRetrofitInstance(httpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun <T : Any> createService(`class`: Class<T>): T = retrofit.create(`class`)
}