package com.evotwin.utils

class PromptEngine {
    private val habits = mutableMapOf<String, Int>()

    fun recordHabit(text: String) {
        val words = text.lowercase().split(" ").filter { it.length > 3 }
        words.forEach { word ->
            habits[word] = habits.getOrDefault(word, 0) + 1
        }
    }

    fun build(base: String, memory: String, personality: String = "Helpful Digital Twin"): String {
        val commonWords = habits.entries.sortedByDescending { it.value }.take(5).joinToString(", ") { it.key }

        return """
            System: You are EvoTwin, a personal AI assistant.
            Personality: ${personality}
            User Habits (Common Words): ${if (commonWords.isEmpty()) "None yet" else commonWords}

            Past Conversations:
            ${memory}

            Current User Message: ${base}
            AI:
        """.trimIndent()
    }
}
