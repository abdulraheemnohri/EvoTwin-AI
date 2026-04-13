package com.evotwin.evolution

import org.junit.Assert.assertEquals
import org.junit.Test

class EvolutionEngineTest {
    @Test
    fun testEvolutionLevels() {
        val engine = EvolutionEngine()

        assertEquals("Basic", engine.level())

        repeat(11) { engine.reward("input", "long response here to trigger reward") }
        assertEquals("Adaptive", engine.level())

        repeat(40) { engine.reward("input", "long response here to trigger reward") }
        assertEquals("Evolving AI", engine.level())

        engine.punish("input", "bad response")
        // Score is now 50 (11 + 40 - 1), still "Evolving AI" if >= 50
        assertEquals("Evolving AI", engine.level())
    }
}
