package com.evotwin.utils

class PromptEngine {
    fun build(base: String, memory: String, personality: String = "Helpful Digital Twin"): String {
        return """
            System: You are EvoTwin, a personal AI assistant.
            Personality: ${personality}

            Past Conversations:
            ${memory}

            Current User Message: ${base}
            AI:
        """.trimIndent()
    }
}
