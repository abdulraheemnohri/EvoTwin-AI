package com.evotwin.ai

class ResponseSynthesisEngine {
    fun synthesize(plan: List<TaskGraphBuilder.TaskStep>, rawResponse: String, evaluation: CritiqueEngine.Evaluation): String {
        return if (evaluation.score < 5) {
            "Thinking error: ${evaluation.critique}. Retrying..."
        } else {
            rawResponse
        }
    }
}
