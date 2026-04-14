package com.evotwin.ai

class PredictiveEngine {
    fun predictNextAction(habits: Map<String, Int>): String? {
        val topHabit = habits.entries.maxByOrNull { it.value }?.key
        return if (topHabit == "whatsapp") "Open WhatsApp (Predicted)" else null
    }
}
