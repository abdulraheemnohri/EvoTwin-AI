package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VoiceControlPanel(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Voice Intelligence", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Voice Cloning Status: Ready", style = MaterialTheme.typography.titleMedium)
        LinearProgressIndicator(progress = 1.0f, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))

        Spacer(Modifier.height(16.dp))
        Text("Voice Settings", style = MaterialTheme.typography.titleMedium)
        ListItem(
            headlineContent = { Text("Natural Voice Output") },
            trailingContent = { Switch(checked = true, onCheckedChange = {}) }
        )
        ListItem(
            headlineContent = { Text("Tone Matching") },
            trailingContent = { Switch(checked = true, onCheckedChange = {}) }
        )

        Button(onClick = {}, modifier = Modifier.padding(top = 16.dp)) {
            Text("Re-train Voice Model")
        }
    }
}
