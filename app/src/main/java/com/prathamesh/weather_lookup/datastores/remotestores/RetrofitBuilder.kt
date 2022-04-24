package com.prathamesh.weather_lookup.datastores.remotestores

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TIME_OUT = 15L

    private val okHttpClient: OkHttpClient by lazy { getOkHttpClientInstance() }

    fun <T> buildGeneric(service: Class<T>, baseUrl: String): T {
        return getRetrofit(baseUrl).create(service)
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClientInstance(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}