package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    var selectedTone by remember { mutableStateOf("Balanced") }
    var voiceCloningEnabled by remember { mutableStateOf(true) }
    var importanceThreshold by remember { mutableFloatStateOf(50f) }

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("System Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("AI Tone Preference", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Casual", "Balanced", "Professional").forEach { tone ->
                FilterChip(
                    selected = selectedTone == tone,
                    onClick = { selectedTone = tone },
                    label = { Text(tone) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        ListItem(
            headlineContent = { Text("Voice Cloning") },
            supportingContent = { Text("AI mimics user's voice patterns") },
            trailingContent = { Switch(checked = voiceCloningEnabled, onCheckedChange = { voiceCloningEnabled = it }) }
        )

        Spacer(Modifier.height(16.dp))
        Text("Memory Importance Threshold: ${importanceThreshold.toInt()} chars", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = importanceThreshold,
            onValueChange = { importanceThreshold = it },
            valueRange = 10f..200f
        )

        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Local Memory")
        }
    }
}
