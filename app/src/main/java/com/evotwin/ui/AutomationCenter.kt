package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AutomationCenter(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Automation Center", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Active Scripts:", style = MaterialTheme.typography.titleMedium)
        ListItem(
            headlineContent = { Text("WhatsApp Auto-Reply") },
            trailingContent = { Switch(checked = true, onCheckedChange = {}) }
        )
        ListItem(
            headlineContent = { Text("Smart Notifications") },
            trailingContent = { Switch(checked = false, onCheckedChange = {}) }
        )
    }
}
