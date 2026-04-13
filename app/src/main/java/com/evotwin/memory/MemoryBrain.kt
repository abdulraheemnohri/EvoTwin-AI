package com.evotwin.memory

class MemoryBrain {
    private val store = mutableListOf<Pair<String, FloatArray>>()

    fun add(text: String, embedding: FloatArray) {
        store.add(text to embedding)
    }

    /**
     * Basic similarity search mock using keyword matching.
     */
    fun search(query: String): String {
        val keywords = query.lowercase().split(" ").filter { it.length > 3 }
        if (keywords.isEmpty()) return ""

        val match = store.find { (text, _) ->
            keywords.any { kw -> text.lowercase().contains(kw) }
        }

        return match?.first ?: ""
    }
}
