package com.example.pertemuan9_restapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    const val BASE_URL = "https://apitani.burunghantu.id/sub/restapi-slim/public/"

    val instance: APIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory
            .create()).build()
        retrofit.create(APIService::class.java)
    }
}