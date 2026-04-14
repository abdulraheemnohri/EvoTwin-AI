package com.evotwin.utils

import org.junit.Assert.assertTrue
import org.junit.Test

class PromptEngineTest {
    @Test
    fun testPromptBuildingWithHabits() {
        val engine = PromptEngine()
        engine.recordHabit("Hello world world world")

        val prompt = engine.build("How are you", "Old memory", "Friendly")

        assertTrue(prompt.contains("Personality: Friendly"))
        assertTrue(prompt.contains("User Habits: world"))
        assertTrue(prompt.contains("Message: How are you"))
    }
}
