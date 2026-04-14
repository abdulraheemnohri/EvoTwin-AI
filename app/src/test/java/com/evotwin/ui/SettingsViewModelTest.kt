package com.evotwin.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class SettingsViewModelTest {
    @Test
    fun testPersonalityModeTransitions() {
        // ViewModel logic test
        val viewModel = SettingsViewModel(null as com.evotwin.ai.LiteRtEngine?)

        viewModel.updatePersonality("Work")
        assertEquals("Work", viewModel.personalityMode)
        assertEquals("Professional", viewModel.selectedTone)
    }
}
