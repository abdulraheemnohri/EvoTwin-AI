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
fun MemoryViewer(memoryDB: MemoryDB, modifier: Modifier = Modifier) {
    // In a real app, we'd use a ViewModel for this.
    // For this mock, we just get recent memories.
    val memories = remember { memoryDB.getRecent().split("\n").filter { it.isNotBlank() } }

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Semantic Memory Viewer", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(memories) { memory ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(memory, Modifier.padding(8.dp))
                }
            }
        }
    }
}
