package com.evotwin.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    var selectedTone by mutableStateOf("Balanced")
    var voiceCloningEnabled by mutableStateOf(true)
    var importanceThreshold by mutableFloatStateOf(50f)
    var personalityMode by mutableStateOf("Casual") // Work, Casual, Business
    var isModelLoaded by mutableStateOf(false)
    var backgroundAgentEnabled by mutableStateOf(false)

    fun updatePersonality(mode: String) {
        personalityMode = mode
        when (mode) {
            "Work" -> selectedTone = "Professional"
            "Casual" -> selectedTone = "Casual"
            "Business" -> selectedTone = "Professional"
        }
    }
}
