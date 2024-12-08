package com.example.proyectofinaltrimestre.models

data class MarvelResponse<T>(
    val data: MarvelData<T>
)

data class MarvelData<T>(
    val results: List<T>
)

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)