package com.example.casereportsystem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.casereportsystem.models.Customer
import com.example.casereportsystem.Repositorys.CaseRepository
import kotlinx.coroutines.launch

@Composable
fun AddCustomerScreen(onCustomerAdded: () -> Unit, onCancel: () -> Unit) {
    val repository = remember { CaseRepository() }
    val coroutineScope = rememberCoroutineScope()

    // State variables for what the user types
    var companyName by remember { mutableStateOf("") }
    var contactPerson by remember { mutableStateOf("") }
    var notificationEmail by remember { mutableStateOf("") }
    var totalQuota by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Add New Customer", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contactPerson,
            onValueChange = { contactPerson = it },
            label = { Text("Contact Person") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = notificationEmail,
            onValueChange = { notificationEmail = it },
            label = { Text("Notification Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = totalQuota,
            onValueChange = { totalQuota = it },
            label = { Text("Total Quota Purchased") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // Convert the text they typed into an Integer (defaults to 0 if they typed letters)
                    val quotaInt = totalQuota.toIntOrNull() ?: 0

                    // Generate a random ID starting with 'cust_'
                    val randomId = "cust_" + (1..10).map { (('a'..'z') + ('A'..'Z') + ('0'..'9')).random() }.joinToString("")

                    val newCustomer = Customer(
                        id = randomId,
                        companyName = companyName,
                        contactPerson = contactPerson,
                        notificationEmail = notificationEmail,
                        quotaLeft = quotaInt, // They start with the full amount left
                    )

                    repository.createCustomer(newCustomer)
                    onCustomerAdded() // Navigate back
                }
            }) {
                Text("Save Customer")
            }
        }
    }
}