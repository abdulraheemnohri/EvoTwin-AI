package com.evotwin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.evotwin.ai.AiEngine
import com.evotwin.ai.LiteRtEngine
import com.evotwin.evolution.EvolutionEngine
import com.evotwin.memory.MemoryBrain
import com.evotwin.memory.MemoryDB
import com.evotwin.ui.ChatScreen
import com.evotwin.ui.ChatViewModel
import com.evotwin.utils.PromptEngine
import com.evotwin.voice.VoiceEngine

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

        val aiEngine = AiEngine(
            liteRtEngine,
            memoryDB,
            memoryBrain,
            promptEngine,
            evolutionEngine,
            voiceEngine
        )

        chatViewModel = ChatViewModel(aiEngine)

        setContent {
            ChatScreen(chatViewModel)
        }
    }
}
