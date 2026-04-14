package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("System Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("Personality Mode", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Work", "Casual", "Business").forEach { mode ->
                FilterChip(
                    selected = viewModel.personalityMode == mode,
                    onClick = { viewModel.updatePersonality(mode) },
                    label = { Text(mode) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("AI Tone Preference", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Casual", "Balanced", "Professional").forEach { tone ->
                FilterChip(
                    selected = viewModel.selectedTone == tone,
                    onClick = { viewModel.selectedTone = tone },
                    label = { Text(tone) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        ListItem(
            headlineContent = { Text("Voice Intelligence") },
            supportingContent = { Text("Continuous Listening & Cloning") },
            trailingContent = { Switch(checked = viewModel.voiceCloningEnabled, onCheckedChange = { viewModel.voiceCloningEnabled = it }) }
        )

        Spacer(Modifier.height(16.dp))
        Text("Memory Importance Threshold: ${viewModel.importanceThreshold.toInt()} chars", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = viewModel.importanceThreshold,
            onValueChange = { viewModel.importanceThreshold = it },
            valueRange = 10f..200f
        )

        Spacer(Modifier.height(24.dp))
        HorizontalDivider()

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Wipe Semantic Memory")
        }
    }
}
