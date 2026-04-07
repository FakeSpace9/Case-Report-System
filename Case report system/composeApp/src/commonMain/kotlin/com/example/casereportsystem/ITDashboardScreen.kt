package com.example.casereportsystem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card // Changed to material3
import androidx.compose.material3.MaterialTheme // Changed to material3
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text // Changed to material3
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.casereportsystem.models.Case
import com.example.casereportsystem.Repositorys.CaseRepository


@Composable
fun ITDashboardScreen(onCreateNewClick: () -> Unit, onAddCustomerClick: () -> Unit) {
    val repository = remember { CaseRepository() }
    val cases by repository.getActiveCases().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "IT Support Dashboard", style = MaterialTheme.typography.headlineSmall)

        // Put the buttons in a Row side-by-side
        Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onCreateNewClick) {
                Text("Create New Case")
            }
            // The new Add Customer Button
            OutlinedButton(onClick = onAddCustomerClick) {
                Text("Add Customer")
            }
        }

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