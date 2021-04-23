package com.sagrishin.photogridlayout.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object UnsplashClient {

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}
