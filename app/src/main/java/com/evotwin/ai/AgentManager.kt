package com.evotwin.ai

import com.evotwin.memory.MemoryDB
import com.evotwin.memory.MemoryBrain
import com.evotwin.automation.AutomationService

class AgentManager(
    private val memoryDB: MemoryDB,
    private val memoryBrain: MemoryBrain
) {
    fun processIntent(input: String): IntentResult {
        // Simple intent parser mock
        return when {
            input.contains("remind", ignoreCase = true) -> IntentResult.Automation("Reminder set.")
            input.contains("remember", ignoreCase = true) -> IntentResult.Memory("I will remember that.")
            else -> IntentResult.GeneralChat
        }
    }

    sealed class IntentResult {
        object GeneralChat : IntentResult()
        data class Automation(val action: String) : IntentResult()
        data class Memory(val confirmation: String) : IntentResult()
    }
}
