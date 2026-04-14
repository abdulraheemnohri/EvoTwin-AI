package com.evotwin.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class AutomationViewModel : ViewModel() {
    val tasks = mutableStateListOf<AutomationTask>()

    init {
        tasks.add(AutomationTask("Intent Decomposed", "Success"))
        tasks.add(AutomationTask("Action Identified", "Ready"))
        tasks.add(AutomationTask("Execution", "Idle"))
    }

    fun updateTask(name: String, status: String) {
        val index = tasks.indexOfFirst { it.name == name }
        if (index != -1) {
            tasks[index] = AutomationTask(name, status)
        }
    }
}

data class AutomationTask(val name: String, val status: String)
