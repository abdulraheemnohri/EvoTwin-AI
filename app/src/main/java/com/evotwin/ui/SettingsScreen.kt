package com.evotwin.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let { viewModel.uploadModel(context, it) }
    }

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("System Settings", style = MaterialTheme.typography.headlineMedium)

        if (viewModel.statusMessage.isNotEmpty()) {
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (viewModel.statusMessage.contains("...")) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(viewModel.statusMessage, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

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
            onClick = { launcher.launch(arrayOf("*/*")) },
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
