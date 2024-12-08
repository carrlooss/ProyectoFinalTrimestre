package com.example.proyectofinaltrimestre.providers.db

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelApiClient {

    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    fun create(publicKey: String, privateKey: String): MarvelApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(MarvelAuthInterceptor(publicKey, privateKey))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MarvelApiService::class.java)
    }
}
