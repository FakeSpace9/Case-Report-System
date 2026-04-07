package com.example.casereportsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import casereportsystem.composeapp.generated.resources.Res
import casereportsystem.composeapp.generated.resources.compose_multiplatform
/*
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
*/
@Composable
fun App() {
    MaterialTheme {
        Surface {
            var currentScreen by remember { mutableStateOf("dashboard") }

            when (currentScreen) {
                "dashboard" -> {
                    ITDashboardScreen(
                        onCreateNewClick = { currentScreen = "create" },
                        onAddCustomerClick = { currentScreen = "add_customer" } // New Route!
                    )
                }
                "create" -> {
                    CreateCaseScreen(
                        onCaseCreated = { currentScreen = "dashboard" }
                    )
                }
                "add_customer" -> {
                    AddCustomerScreen(
                        onCustomerAdded = { currentScreen = "dashboard" },
                        onCancel = { currentScreen = "dashboard" } // Also go back if they hit cancel
                    )
                }
            }
        }
    }
}