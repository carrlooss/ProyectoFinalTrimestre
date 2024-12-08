package com.example.proyectofinaltrimestre.providers.db

import com.example.proyectofinaltrimestre.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.proyectofinaltrimestre.models.Character

interface MarvelApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): MarvelResponse<Character>
}