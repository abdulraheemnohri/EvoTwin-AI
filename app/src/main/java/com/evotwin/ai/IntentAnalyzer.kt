package com.evotwin.ai

class IntentAnalyzer {
    fun analyze(input: String): IntentProfile {
        return when {
            input.contains("remind", ignoreCase = true) || input.contains("schedule", ignoreCase = true) ->
                IntentProfile(type = IntentType.AUTOMATION, complexity = 2)
            input.contains("who is", ignoreCase = true) || input.contains("what was", ignoreCase = true) ->
                IntentProfile(type = IntentType.MEMORY_RECALL, complexity = 1)
            else ->
                IntentProfile(type = IntentType.CONVERSATION, complexity = 1)
        }
    }

    enum class IntentType { CONVERSATION, AUTOMATION, MEMORY_RECALL }
    data class IntentProfile(val type: IntentType, val complexity: Int)
}
