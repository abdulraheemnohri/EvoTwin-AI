package com.evotwin.memory

class MemoryBrain {
    private val store = mutableListOf<MemoryItem>()

    fun add(text: String, embedding: FloatArray, importance: Int = 0) {
        store.add(MemoryItem(text, embedding, importance))
    }

    fun search(query: String): String {
        val keywords = query.lowercase().split(" ").filter { it.length > 3 }
        if (keywords.isEmpty()) return ""

        // Sort by importance then by keyword match
        val match = store
            .sortedByDescending { it.importance }
            .find { item ->
                keywords.any { kw -> item.text.lowercase().contains(kw) }
            }

        return match?.text ?: ""
    }

    data class MemoryItem(val text: String, val embedding: FloatArray, val importance: Int)
}
