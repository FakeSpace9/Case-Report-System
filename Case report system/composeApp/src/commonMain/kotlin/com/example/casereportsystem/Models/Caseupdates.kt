package com.example.casereportsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class CaseUpdate(
    val id: String = "",
    val caseId: String = "",
    val message: String = "",
    val authorUid: String = "",
    val authorName: String = "",
    val updateType: String = "reply", // "reply", "status_change", "takeover"
    val timestamp: Long = 0L
)