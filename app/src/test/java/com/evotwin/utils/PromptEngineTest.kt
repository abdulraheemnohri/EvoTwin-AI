package com.evotwin.utils

import org.junit.Assert.assertTrue
import org.junit.Test

class PromptEngineTest {
    @Test
    fun testPromptBuilding() {
        val engine = PromptEngine()
        val base = "Hello"
        val memory = "Old conversation"
        val personality = "Friendly"

        val prompt = engine.build(base, memory, personality)

        assertTrue(prompt.contains("Personality: Friendly"))
        assertTrue(prompt.contains("Past Conversations:\nOld conversation"))
        assertTrue(prompt.contains("Current User Message: Hello"))
    }
}
