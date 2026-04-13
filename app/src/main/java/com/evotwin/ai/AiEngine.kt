package com.evotwin.ai

import com.evotwin.evolution.EvolutionEngine
import com.evotwin.memory.MemoryDB
import com.evotwin.memory.MemoryBrain
import com.evotwin.utils.PromptEngine
import com.evotwin.voice.VoiceEngine
import com.evotwin.automation.AutomationManager

class AiEngine(
    private val lite: LiteRtEngine,
    private val memoryDB: MemoryDB,
    private val brain: MemoryBrain,
    private val promptEngine: PromptEngine,
    private val evolutionEngine: EvolutionEngine,
    private val voiceEngine: VoiceEngine,
    private val automationManager: AutomationManager
) {
    private val agentManager = AgentManager(memoryDB, brain)

    fun chat(input: String): String {
        // Record habit
        promptEngine.recordHabit(input)

        val intent = agentManager.processIntent(input)

        val recentMemory = memoryDB.getRecent()
        val semanticMemory = brain.search(input)

        val personality = "Level: ${evolutionEngine.level()} - Personal and efficient."

        val prompt = when (intent) {
            is AgentManager.IntentResult.GeneralChat -> {
                promptEngine.build(
                    base = input,
                    memory = recentMemory + "\n" + semanticMemory,
                    personality = personality
                )
            }
            is AgentManager.IntentResult.Automation -> {
                automationManager.execute(intent.action)
                "System Action: ${intent.action} executed. User Context: $input"
            }
            is AgentManager.IntentResult.Memory -> {
                "Memory stored: ${intent.confirmation}. Context: $input"
            }
        }

        val response = lite.generate(prompt)

        // Calculate importance (mock: messages > 50 chars are important)
        val importance = if (input.length > 50) 1 else 0

        // Save to structured memory
        memoryDB.save(input, response, importance)

        // Add to semantic memory
        brain.add(input, floatArrayOf(0f), importance)

        // Voice output
        voiceEngine.speak(response)

        // Evolution feedback
        if (response.length > 20) {
            evolutionEngine.reward(input, response)
        } else if (response.contains("sorry", ignoreCase = true)) {
            evolutionEngine.punish(input, response)
        }

        return response
    }
}
