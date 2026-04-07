package com.example.casereportsystem

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.casereportsystem.models.Case
import com.example.casereportsystem.Repositorys.CaseRepository
import kotlinx.coroutines.launch

@Composable
fun CreateCaseScreen(onCaseCreated: () -> Unit) {
    val repository = remember { CaseRepository() }

    // We need a coroutine scope to call 'suspend' functions like createCase()
    val coroutineScope = rememberCoroutineScope()

    // These variables remember what the user is typing into the boxes
    var customerName by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Create New Case", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Issue Title (e.g., Server Down)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Detailed Description") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3 // Makes the text box taller for long descriptions
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    // 1. Generate a random 20-character ID for this case
                    val randomId = (1..20).map { (('a'..'z') + ('A'..'Z') + ('0'..'9')).random() }.joinToString("")

                    // 2. Build the Case object using what the user typed
                    val newCase = Case(
                        id = randomId,
                        customerId = "cust_test", // Dummy ID for now
                        customerName = customerName,
                        title = title,
                        description = description,
                        status = "open",
                        createdByUid = "reception", // Dummy user for now
                        createdAt = 0L
                    )

                    // 3. Send it to Firebase!
                    repository.createCase(newCase)

                    // 4. Trigger the navigation to go back to the dashboard
                    onCaseCreated()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Case")
        }
    }
}