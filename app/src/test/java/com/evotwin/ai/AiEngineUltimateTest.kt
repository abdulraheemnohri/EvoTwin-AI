package com.evotwin.ai

import org.junit.Assert.assertNotNull
import org.junit.Test

class AiEngineUltimateTest {
    @Test
    fun testAiEngineInitialization() {
        val analyzer = IntentAnalyzer()
        val graph = TaskGraphBuilder()
        val critique = CritiqueEngine()

        assertNotNull(analyzer)
        assertNotNull(graph)
        assertNotNull(critique)
    }
}
