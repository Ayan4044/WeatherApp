package com.example.weatherapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitSingleton {

    private const val BASE_URL = "https://weatherbit-v1-mashape.p.rapidapi.com/"
    private var okHttpClient: OkHttpClient =OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY))
        .build()

    val instance: APIInterface by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(APIInterface::class.java)

    }
}
