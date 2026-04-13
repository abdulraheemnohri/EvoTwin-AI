package com.evotwin.ai

import com.evotwin.evolution.EvolutionEngine
import com.evotwin.memory.MemoryDB
import com.evotwin.memory.MemoryBrain
import com.evotwin.utils.PromptEngine
import com.evotwin.voice.VoiceEngine

class AiEngine(
    private val lite: LiteRtEngine,
    private val memoryDB: MemoryDB,
    private val brain: MemoryBrain,
    private val promptEngine: PromptEngine,
    private val evolutionEngine: EvolutionEngine,
    private val voiceEngine: VoiceEngine
) {
    fun chat(input: String): String {
        val recentMemory = memoryDB.getRecent()
        val semanticMemory = brain.search(input)

        val personality = "Level: ${evolutionEngine.level()} - Personal and efficient."

        val prompt = promptEngine.build(
            base = input,
            memory = recentMemory + "\n" + semanticMemory,
            personality = personality
        )

        val response = lite.generate(prompt)

        // Save to structured memory
        memoryDB.save(input, response)

        // Add to semantic memory (mock embedding)
        brain.add(input, floatArrayOf(0f))

        // Voice output
        voiceEngine.speak(response)

        // Basic evolution feedback (reward for long responses as a proxy for detail)
        if (response.length > 20) {
            evolutionEngine.reward()
        }

        return response
    }
}
