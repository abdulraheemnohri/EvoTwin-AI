package com.evotwin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.evotwin.ai.AiEngine
import com.evotwin.ai.LiteRtEngine
import com.evotwin.evolution.EvolutionEngine
import com.evotwin.memory.MemoryBrain
import com.evotwin.memory.MemoryDB
import com.evotwin.ui.MainScreen
import com.evotwin.ui.ChatViewModel
import com.evotwin.utils.PromptEngine
import com.evotwin.voice.VoiceEngine
import com.evotwin.automation.AutomationManager

class MainActivity : ComponentActivity() {
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize dependencies
        val liteRtEngine = LiteRtEngine(this)
        val memoryDB = MemoryDB(this)
        val memoryBrain = MemoryBrain()
        val promptEngine = PromptEngine()
        val evolutionEngine = EvolutionEngine()
        val voiceEngine = VoiceEngine(this)
        val automationManager = AutomationManager(this)

        val aiEngine = AiEngine(
            liteRtEngine,
            memoryDB,
            memoryBrain,
            promptEngine,
            evolutionEngine,
            voiceEngine,
            automationManager
        )

        chatViewModel = ChatViewModel(aiEngine)

        setContent {
            MainScreen(chatViewModel)
        }
    }
}
