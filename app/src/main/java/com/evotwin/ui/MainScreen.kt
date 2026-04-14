package com.evotwin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.evotwin.R
import com.evotwin.memory.MemoryDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    chatViewModel: ChatViewModel,
    memoryDB: MemoryDB,
    evolutionViewModel: EvolutionViewModel,
    automationViewModel: AutomationViewModel,
    settingsViewModel: SettingsViewModel
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Chat", "Memory", "Evolution", "Voice", "Automation", "Settings")
    val icons = listOf(
        Icons.Default.Email, Icons.Default.DateRange, Icons.Default.Star,
        Icons.Default.Face, Icons.Default.Build, Icons.Default.Settings
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(32.dp).padding(end = 8.dp)
                        )
                        Text("EvoTwin AGI", style = MaterialTheme.typography.titleLarge)
                    }
                }
            )
        },
        bottomBar = {
            ScrollableTabRow(selectedTabIndex = selectedItem) {
                items.forEachIndexed { index, item ->
                    Tab(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        text = { Text(item, style = MaterialTheme.typography.labelSmall) },
                        icon = { Icon(icons[index], contentDescription = item, modifier = Modifier.size(20.dp)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> ChatScreen(chatViewModel)
                1 -> MemoryDashboard(memoryDB)
                2 -> EvolutionAnalyticsScreen(evolutionViewModel)
                3 -> VoiceIntelligenceScreen()
                4 -> AutomationCenter(automationViewModel)
                5 -> SettingsScreen(settingsViewModel)
            }
        }
    }
}
