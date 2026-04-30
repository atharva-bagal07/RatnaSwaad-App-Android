package com.example.ratnaswaad.data

data class User(
    val uid: String = "",
    val phone: String? = null,
    val name: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)