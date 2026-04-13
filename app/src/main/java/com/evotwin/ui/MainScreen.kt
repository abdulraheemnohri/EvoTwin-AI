package com.evotwin.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.evotwin.memory.MemoryDB

@Composable
fun MainScreen(chatViewModel: ChatViewModel, memoryDB: MemoryDB) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Chat", "Memory", "Evolution", "Voice", "Automation", "Settings")
    val icons = listOf(
        Icons.Filled.Chat,
        Icons.Filled.Storage,
        Icons.Filled.AutoAwesome,
        Icons.Filled.RecordVoiceOver,
        Icons.Filled.SmartToy,
        Icons.Filled.Settings
    )

    Scaffold(
        bottomBar = {
            ScrollableTabRow(selectedTabIndex = selectedItem) {
                items.forEachIndexed { index, item ->
                    Tab(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        text = { Text(item) },
                        icon = { Icon(icons[index], contentDescription = item) }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> ChatScreen(chatViewModel, Modifier.padding(innerPadding))
            1 -> MemoryViewer(memoryDB, Modifier.padding(innerPadding))
            2 -> EvolutionDashboard(Modifier.padding(innerPadding))
            3 -> VoiceControlPanel(Modifier.padding(innerPadding))
            4 -> AutomationCenter(Modifier.padding(innerPadding))
            5 -> SettingsScreen(Modifier.padding(innerPadding))
        }
    }
}
