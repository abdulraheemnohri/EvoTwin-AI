package com.evotwin.ai

class TaskGraphBuilder {
    fun buildPlan(profile: IntentAnalyzer.IntentProfile, context: String): List<TaskStep> {
        val steps = mutableListOf<TaskStep>()
        when (profile.type) {
            IntentAnalyzer.IntentType.AUTOMATION -> {
                steps.add(TaskStep("Identify app target", "Parsing intent for target application"))
                steps.add(TaskStep("Execute automation", "Triggering system action for $context"))
            }
            IntentAnalyzer.IntentType.MEMORY_RECALL -> {
                steps.add(TaskStep("Query vector brain", "Searching semantic memory"))
                steps.add(TaskStep("Retrieve SQL context", "Fetching structured chat history"))
            }
            IntentAnalyzer.IntentType.CONVERSATION -> {
                steps.add(TaskStep("Reasoning", "Generating natural language response"))
            }
        }
        return steps
    }

    data class TaskStep(val action: String, val detail: String)
}
