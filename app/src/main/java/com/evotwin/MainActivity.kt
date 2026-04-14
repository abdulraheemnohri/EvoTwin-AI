package com.evotwin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.evotwin.ai.AiEngine
import com.evotwin.ai.LiteRtEngine
import com.evotwin.evolution.EvolutionEngine
import com.evotwin.memory.MemoryBrain
import com.evotwin.memory.MemoryDB
import com.evotwin.ui.*
import com.evotwin.utils.PromptEngine
import com.evotwin.voice.VoiceEngine
import com.evotwin.automation.AutomationManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val evolutionEngine = EvolutionEngine()
        val liteRtEngine = LiteRtEngine(this)
        val memoryDB = MemoryDB(this)
        val memoryBrain = MemoryBrain()
        val promptEngine = PromptEngine()
        val voiceEngine = VoiceEngine(this)
        val automationManager = AutomationManager(this)

        val evolutionViewModel = EvolutionViewModel(evolutionEngine)
        val automationViewModel = AutomationViewModel()
        val settingsViewModel = SettingsViewModel(liteRtEngine)

        val aiEngine = AiEngine(
            liteRtEngine,
            memoryDB,
            memoryBrain,
            promptEngine,
            evolutionEngine,
            voiceEngine,
            automationManager,
            evolutionViewModel,
            automationViewModel,
            settingsViewModel
        )

        val chatViewModel = ChatViewModel(aiEngine)

        setContent {
            EvoTwinTheme {
                MainScreen(
                    chatViewModel,
                    memoryDB,
                    evolutionViewModel,
                    automationViewModel,
                    settingsViewModel
                )
            }
        }
    }
}
