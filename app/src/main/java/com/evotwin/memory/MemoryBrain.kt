package com.evotwin.memory

class MemoryBrain {
    private val store = mutableListOf<Pair<String, FloatArray>>()

    fun add(text: String, embedding: FloatArray) {
        store.add(text to embedding)
    }

    fun search(query: String): String {
        return store.lastOrNull()?.first ?: ""
    }
}