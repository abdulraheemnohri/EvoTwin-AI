package com.evotwin.evolution

class EvolutionEngine {
    private var score = 0
    private val trainingLoop = SelfTrainingLoop()

    fun reward(input: String = "", response: String = "") {
        score += 1
        if (input.isNotEmpty() && response.isNotEmpty()) {
            trainingLoop.record(input, response, 1)
        }
    }

    fun punish(input: String = "", response: String = "") {
        score -= 1
        if (input.isNotEmpty() && response.isNotEmpty()) {
            trainingLoop.record(input, response, -1)
        }
    }

    fun level(): String {
        return when {
            score < 10 -> "Basic"
            score < 50 -> "Adaptive"
            else -> "Evolving AI"
        }
    }
}
