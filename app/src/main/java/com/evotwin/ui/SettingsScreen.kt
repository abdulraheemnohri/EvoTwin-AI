package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("System Settings", style = MaterialTheme.typography.headlineMedium)

        if (viewModel.statusMessage.isNotEmpty()) {
            Text(viewModel.statusMessage, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
        }

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
        ListItem(
            headlineContent = { Text("Voice Intelligence") },
            supportingContent = { Text("Continuous Listening & Cloning") },
            trailingContent = { Switch(checked = viewModel.voiceCloningEnabled, onCheckedChange = { viewModel.voiceCloningEnabled = it }) }
        )

        ListItem(
            headlineContent = { Text("Background AI Agent") },
            supportingContent = { Text("AI acts before you ask") },
            trailingContent = { Switch(checked = viewModel.backgroundAgentEnabled, onCheckedChange = { viewModel.backgroundAgentEnabled = it }) }
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
        Text("Model Management", style = MaterialTheme.typography.titleLarge)
        Text("Status: ${if (viewModel.isModelLoaded) "Active" else "Model Not Found"}",
            color = if (viewModel.isModelLoaded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)

        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { viewModel.downloadModel(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Download Gemma Model (2.5GB)")
        }

        OutlinedButton(
            onClick = {
                // Mock upload with a dummy file for testing logic
                val dummyFile = File(context.cacheDir, "mock_model.litertlm")
                if (!dummyFile.exists()) dummyFile.writeText("mock content")
                viewModel.uploadModel(context, dummyFile)
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Upload Model from storage")
        }

        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Wipe Semantic Memory")
        }
    }
}
