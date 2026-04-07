package com.example.casereportsystem.Repositorys

import com.example.casereportsystem.models.Case
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CaseRepository {
    // 1. Get a reference to the Firestore database
    private val db = Firebase.firestore

    // 2. Get a reference to the "cases" collection
    private val casesCollection = db.collection("cases")

    private val customersCollection = db.collection("customers")
    /**
     * Creates a new case in the database.
     * The Receptionist app will call this function.
     */
    suspend fun createCase(newCase: Case) {
        try {
            // Firebase will automatically convert the 'Case' data class to a JSON document
            casesCollection.document(newCase.id).set(newCase)
        } catch (e: Exception) {
            println("Error creating case: ${e.message}")
        }
    }

    /**
     * Gets a real-time stream of all "open" or "in_progress" cases.
     * The IT Dashboard will observe this Flow. If a new case is added,
     * the UI will update automatically!
     */
    fun getActiveCases(): Flow<List<Case>> {
        return casesCollection
            .where { "status" equalTo "open" } // You can expand this to include "in_progress"
            .snapshots
            .map { querySnapshot ->
                // Convert the Firebase snapshot back into a List of your Kotlin 'Case' objects
                querySnapshot.documents.map { it.data<Case>() }
            }
    }

    /**
     * Updates an existing case (e.g., when IT staff takes over or resolves it)
     */
    suspend fun updateCaseStatus(caseId: String, newStatus: String, itUid: String, itName: String) {
        try {
            casesCollection.document(caseId).update(
                "status" to newStatus,
                "assignedItUid" to itUid,
                "assignedItName" to itName
            )
        } catch (e: Exception) {
            println("Error updating case: ${e.message}")
        }
    }
    /**
     * Creates a new customer in the database.
     */
    suspend fun createCustomer(newCustomer: com.example.casereportsystem.models.Customer) {
        try {
            customersCollection.document(newCustomer.id).set(newCustomer)
        } catch (e: Exception) {
            println("Error creating customer: ${e.message}")
        }
    }
}