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
    val promptEngine: PromptEngine,
    private val evolutionEngine: EvolutionEngine,
    private val voiceEngine: VoiceEngine,
    private val automationManager: AutomationManager
) {
    private val intentAnalyzer = IntentAnalyzer()
    private val taskGraphBuilder = TaskGraphBuilder()
    private val critiqueEngine = CritiqueEngine()
    private val synthesisEngine = ResponseSynthesisEngine()

    fun chat(input: String): String {
        // 1. Intent Analysis
        val intentProfile = intentAnalyzer.analyze(input)

        // 2. Planning
        val plan = taskGraphBuilder.buildPlan(intentProfile, input)

        // 3. Context Retrieval
        val recentMemory = memoryDB.getRecent()
        val semanticMemory = brain.search(input)

        // 4. Reasoning / Generation
        val personality = "Level: ${evolutionEngine.level()} - AGI Persona."
        val prompt = promptEngine.build(
            base = input,
            memory = recentMemory + "\n" + semanticMemory,
            personality = personality
        )

        val rawResponse = lite.generate(prompt)

        // 5. Self-Critique
        val evaluation = critiqueEngine.evaluate(input, rawResponse)

        // 6. Response Synthesis
        val finalResponse = synthesisEngine.synthesize(plan, rawResponse, evaluation)

        // 7. Action Execution (if applicable)
        if (intentProfile.type == IntentAnalyzer.IntentType.AUTOMATION) {
            automationManager.execute(input)
        }

        // 8. Memory & Evolution Update
        val importance = if (input.length > 50) 1 else 0
        memoryDB.save(input, finalResponse, importance)
        brain.add(input, floatArrayOf(0f), importance)

        promptEngine.recordHabit(input)
        voiceEngine.speak(finalResponse)

        if (evaluation.score >= 7) {
            evolutionEngine.reward(input, finalResponse)
        } else {
            evolutionEngine.punish(input, finalResponse)
        }

        return finalResponse
    }
}
