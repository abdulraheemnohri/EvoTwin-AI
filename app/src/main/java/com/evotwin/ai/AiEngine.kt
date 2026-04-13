package com.evotwin.ai

import com.evotwin.memory.MemoryDB
import com.evotwin.memory.MemoryBrain
import com.evotwin.utils.PromptEngine

class AiEngine(
    private val lite: LiteRtEngine,
    private val memoryDB: MemoryDB,
    private val brain: MemoryBrain,
    private val promptEngine: PromptEngine
) {
    fun chat(input: String): String {
        val memory = memoryDB.getRecent()
        val semantic = brain.search(input)
        val prompt = promptEngine.build(
            base = input,
            memory = memory + semantic
        )
        val response = lite.generate(prompt)
        memoryDB.save(input, response)
        return response
    }
}