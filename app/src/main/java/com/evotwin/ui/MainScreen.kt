package com.evotwin.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(chatViewModel: ChatViewModel) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Chat", "Evolution", "Automation", "Settings")
    val icons = listOf(Icons.Filled.Chat, Icons.Filled.AutoAwesome, Icons.Filled.SmartToy, Icons.Filled.Settings)

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> ChatScreen(chatViewModel, Modifier.padding(innerPadding))
            1 -> EvolutionDashboard(Modifier.padding(innerPadding))
            2 -> AutomationCenter(Modifier.padding(innerPadding))
            3 -> SettingsScreen(Modifier.padding(innerPadding))
        }
    }
}
