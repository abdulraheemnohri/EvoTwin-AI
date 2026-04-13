package com.evotwin.evolution

class EvolutionEngine {
    private var score = 0

    fun reward() {
        score += 1
    }

    fun punish() {
        score -= 1
    }

    fun level(): String {
        return when {
            score < 10 -> "Basic"
            score < 50 -> "Adaptive"
            else -> "Evolving AI"
        }
    }
}