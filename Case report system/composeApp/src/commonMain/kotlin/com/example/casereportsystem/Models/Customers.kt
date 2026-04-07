package com.example.casereportsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: String = "",
    val companyName: String = "",
    val contactPerson: String = "",
    val phoneNumber: String = "",
    val notificationEmail: String = "",
    val quotaLeft: Int = 0,
)