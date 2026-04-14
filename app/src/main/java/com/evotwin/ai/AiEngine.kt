package com.evotwin.ai

import com.evotwin.evolution.EvolutionEngine
import com.evotwin.memory.MemoryDB
import com.evotwin.memory.MemoryBrain
import com.evotwin.utils.PromptEngine
import com.evotwin.voice.VoiceEngine
import com.evotwin.automation.AutomationManager
import com.evotwin.ui.EvolutionViewModel
import com.evotwin.ui.AutomationViewModel
import com.evotwin.ui.SettingsViewModel

class AiEngine(
    private val lite: LiteRtEngine,
    private val memoryDB: MemoryDB,
    private val brain: MemoryBrain,
    val promptEngine: PromptEngine,
    private val evolutionEngine: EvolutionEngine,
    private val voiceEngine: VoiceEngine,
    private val automationManager: AutomationManager,
    private val evolutionViewModel: EvolutionViewModel,
    private val automationViewModel: AutomationViewModel,
    private val settingsViewModel: SettingsViewModel
) {
    private val intentAnalyzer = IntentAnalyzer()
    private val taskGraphBuilder = TaskGraphBuilder()
    private val critiqueEngine = CritiqueEngine()
    private val synthesisEngine = ResponseSynthesisEngine()

    fun chat(input: String): String {
        // Advanced Personality & Tone Injection
        promptEngine.selectedTone = settingsViewModel.selectedTone
        val systemContext = "Mode: ${settingsViewModel.personalityMode}. Goal: AGI Simulation."

        // 1. Intent Analysis
        val intentProfile = intentAnalyzer.analyze(input)
        automationViewModel.updateTask("Intent Decomposed", "Success")

        // 2. Planning
        val plan = taskGraphBuilder.buildPlan(intentProfile, input)
        automationViewModel.updateTask("Action Identified", "Ready")

        // 3. Context Retrieval
        val recentMemory = memoryDB.getRecent()
        val semanticMemory = brain.search(input)

        // 4. Reasoning / Generation
        val personality = "$systemContext Level: ${evolutionEngine.level()}."
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

        // 7. Tool/Action Execution
        if (intentProfile.type == IntentAnalyzer.IntentType.AUTOMATION) {
            automationViewModel.updateTask("Execution", "Running")
            automationManager.execute(input) // Handles apps, settings, and gestures
            automationViewModel.updateTask("Execution", "Complete")
        }

        // 8. Memory & Evolution Update
        val importance = if (input.length > settingsViewModel.importanceThreshold) 1 else 0
        memoryDB.save(input, finalResponse, importance)
        brain.add(input, floatArrayOf(0f), importance)

        promptEngine.recordHabit(input)
        if (settingsViewModel.voiceCloningEnabled) {
            voiceEngine.speak(finalResponse)
        }

        if (evaluation.score >= 7) {
            evolutionEngine.reward(input, finalResponse)
        } else {
            evolutionEngine.punish(input, finalResponse)
        }

        evolutionViewModel.updateMetrics(0, evolutionEngine.level())

        return finalResponse
    }
}
