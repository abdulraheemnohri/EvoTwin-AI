package com.evotwin.utils

class PromptEngine {
    fun build(base: String, memory: String): String {
        return "$memory\nUser: $base\nAI:"
    }
}