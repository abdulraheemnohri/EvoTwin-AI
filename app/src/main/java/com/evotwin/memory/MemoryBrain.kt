package com.evotwin.memory

class MemoryBrain {
    private val store = mutableListOf<MemoryItem>()

    fun add(text: String, embedding: FloatArray, importance: Int = 0) {
        store.add(MemoryItem(text, embedding, importance, System.currentTimeMillis()))
    }

    /**
     * Enhanced search with dynamic scoring (Importance + Recency + Keyword match).
     */
    fun search(query: String): String {
        val keywords = query.lowercase().split(" ").filter { it.length > 3 }
        if (keywords.isEmpty()) return ""

        val rankedItems = store.map { item ->
            var score = 0f

            // Keyword match (Highest weight)
            if (keywords.any { kw -> item.text.lowercase().contains(kw) }) {
                score += 10f
            }

            // Importance weight
            score += item.importance * 5f

            // Recency weight (normalized)
            val timeDecay = 1f / (1f + (System.currentTimeMillis() - item.timestamp) / 3600000f)
            score += timeDecay * 2f

            item to score
        }.sortedByDescending { it.second }

        return rankedItems.firstOrNull()?.first?.text ?: ""
    }

    data class MemoryItem(val text: String, val embedding: FloatArray, val importance: Int, val timestamp: Long)
}
