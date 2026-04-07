package com.example.casereportsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uid: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val role: String = "", // "reception", "it_staff", "admin"
    val createdAt: Long = 0L
)