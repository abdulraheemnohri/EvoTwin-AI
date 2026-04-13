package com.evotwin.ai

import org.junit.Assert.assertEquals
import org.junit.Test

class IntentAnalyzerTest {
    @Test
    fun testIntentDecomposition() {
        val analyzer = IntentAnalyzer()

        val automationIntent = analyzer.analyze("Schedule a meeting tomorrow")
        assertEquals(IntentAnalyzer.IntentType.AUTOMATION, automationIntent.type)
        assertEquals(2, automationIntent.complexity)

        val memoryIntent = analyzer.analyze("Who is my best friend?")
        assertEquals(IntentAnalyzer.IntentType.MEMORY_RECALL, memoryIntent.type)

        val chatIntent = analyzer.analyze("Hello there")
        assertEquals(IntentAnalyzer.IntentType.CONVERSATION, chatIntent.type)
    }
}
