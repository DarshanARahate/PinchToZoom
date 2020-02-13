package com.kotlinrxjava.demoapp.apis

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


const val BASE_URL: String = "https://api.github.com/"
//const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

object RetrofitClient {

    private lateinit var interceptor: HttpLoggingInterceptor

    fun getClient(): ApiInterfaces {

        interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()

        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterfaces::class.java)
    }


}