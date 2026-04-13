package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AutomationCenter(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Task Monitoring", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("Current Task Plan:", style = MaterialTheme.typography.titleMedium)
        Card(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Column(Modifier.padding(12.dp)) {
                Text("1. Intent Decomposed: Success")
                Text("2. Action Identified: open whatsapp")
                Text("3. Execution: Pending", color = MaterialTheme.colorScheme.primary)
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Active Automation Scripts:", style = MaterialTheme.typography.titleMedium)
        ListItem(
            headlineContent = { Text("WhatsApp Auto-Reply") },
            trailingContent = { Switch(checked = true, onCheckedChange = {}) }
        )
    }
}
