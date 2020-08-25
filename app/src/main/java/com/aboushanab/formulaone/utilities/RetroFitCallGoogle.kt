package com.aboushanab.formulaone.utilities

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitCallGoogle {
    val GOOGLE_BASE_URL = "https://www.googleapis.com/"
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val httpClient = OkHttpClient.Builder()

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    fun retrofit(): Retrofit {
        httpClient.interceptors().add(
            interceptor
        )
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .baseUrl(GOOGLE_BASE_URL)
            .build()
    }
}