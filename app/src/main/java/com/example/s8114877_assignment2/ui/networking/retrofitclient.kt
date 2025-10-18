//package com.example.s8114877_assignment2.ui.networking
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//    private const val BASE_URL = "https://nit3213api.onrender.com/"
//
//    val instance: AppApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(AppApiService::class.java)
//    }
//}
