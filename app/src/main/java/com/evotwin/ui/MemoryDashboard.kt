package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evotwin.memory.MemoryDB

@Composable
fun MemoryDashboard(memoryDB: MemoryDB, modifier: Modifier = Modifier) {
    val memories = remember { memoryDB.getRecent().split("\n").filter { it.isNotBlank() } }

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Memory Intelligence", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("Memory Categories", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SuggestionChip(onClick = {}, label = { Text("Short-term") })
            SuggestionChip(onClick = {}, label = { Text("Long-term") })
            SuggestionChip(onClick = {}, label = { Text("Semantic") })
        }

        Spacer(Modifier.height(16.dp))
        Text("Recent Memories", style = MaterialTheme.typography.titleMedium)
        LazyColumn(Modifier.weight(1f)) {
            items(memories) { memory ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                    Text(memory, Modifier.padding(12.dp))
                }
            }
        }

        Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Text("Run Memory Optimization")
        }
    }
}
