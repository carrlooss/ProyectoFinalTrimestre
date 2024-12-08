package com.example.proyectofinaltrimestre.models

import java.io.Serializable

data class PerfilModel(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val login: String,
    val password: String,
): Serializable


