package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AutomationCenter(viewModel: AutomationViewModel, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Task Monitoring", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("Current Task Plan:", style = MaterialTheme.typography.titleMedium)
        Card(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Column(Modifier.padding(12.dp)) {
                viewModel.tasks.forEach { task ->
                    Text("${task.name}: ${task.status}",
                        color = if (task.status == "Running") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                }
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
