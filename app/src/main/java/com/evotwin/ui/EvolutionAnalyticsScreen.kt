package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EvolutionAnalyticsScreen(viewModel: EvolutionViewModel, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Evolution Analytics", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Current Stage: ${viewModel.level}", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text("Reasoning Accuracy: ${(viewModel.accuracy * 100).toInt()}%")
                LinearProgressIndicator(progress = { viewModel.accuracy }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                Text("Habit Modeling Depth: 82%")
                LinearProgressIndicator(progress = { 0.82f }, modifier = Modifier.fillMaxWidth())
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Behavioral Pattern Matching", style = MaterialTheme.typography.titleMedium)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("• User Decision Logic: Cloned")
                Text("• Response Tone: Matching")
                Text("• Predictability: 94%")
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("View Evolution History")
        }
    }
}
