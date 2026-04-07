package com.example.casereportsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class Case(
    val id: String = "",
    val customerId: String = "",
    val customerName: String = "",
    val createdByUid: String = "",
    val createdByName: String = "",
    val title: String = "",
    val description: String = "",
    val status: String = "open", // "open", "in_progress", "resolved"
    val assignedItUid: String? = null, // Nullable because it starts empty
    val assignedItName: String? = null,
    val createdAt: Long = 0L,
    val resolvedAt: Long? = null // Nullable until resolved
)