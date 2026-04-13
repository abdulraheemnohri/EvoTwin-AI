package com.evotwin.ai

class CritiqueEngine {
    fun evaluate(input: String, response: String): Evaluation {
        val score = when {
            response.isBlank() -> 0
            response.length < 5 -> 2
            response.contains("I'm sorry", ignoreCase = true) -> 5
            else -> 9
        }
        return Evaluation(score, if (score < 5) "Response too short or empty" else "Acceptable")
    }

    data class Evaluation(val score: Int, val critique: String)
}
