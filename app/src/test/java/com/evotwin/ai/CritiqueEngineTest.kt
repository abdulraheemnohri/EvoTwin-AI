package com.evotwin.ai

import org.junit.Assert.assertTrue
import org.junit.Test

class CritiqueEngineTest {
    @Test
    fun testSelfCritiqueLogic() {
        val engine = CritiqueEngine()

        val badEvaluation = engine.evaluate("Tell me a story", "")
        assertTrue(badEvaluation.score < 5)

        val goodEvaluation = engine.evaluate("Hi", "Hello! How can I help you today?")
        assertTrue(goodEvaluation.score >= 7)
    }
}
