package com.example.casereportsystem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card // Changed to material3
import androidx.compose.material3.MaterialTheme // Changed to material3
import androidx.compose.material3.Text // Changed to material3
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.casereportsystem.models.Case
import com.example.casereportsystem.repositories.CaseRepository

@Composable
fun ITDashboardScreen() {
    val repository = remember { CaseRepository() }
    val cases by repository.getActiveCases().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // FIXED: Using MaterialTheme.typography.headlineSmall instead of h5
        Text(text = "IT Support Dashboard", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (cases.isEmpty()) {
            Text("No open cases right now! Good job.")
        } else {
            LazyColumn {
                items(cases) { case ->
                    CaseCard(case)
                }
            }
        }
    }
}

@Composable
fun CaseCard(case: Case) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        // Note: 'elevation' is handled differently in Material 3, so we remove it for this simple card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // FIXED: Using MaterialTheme.typography.titleMedium instead of h6
            Text("Customer: ${case.customerName}", style = MaterialTheme.typography.titleMedium)
            Text("Issue: ${case.title}")
            Text("Status: ${case.status}")
        }
    }
}