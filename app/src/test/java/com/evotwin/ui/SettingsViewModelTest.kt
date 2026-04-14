package com.evotwin.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class SettingsViewModelTest {
    @Test
    fun testPersonalityModeTransitions() {
        val viewModel = SettingsViewModel()

        viewModel.updatePersonality("Work")
        assertEquals("Work", viewModel.personalityMode)
        assertEquals("Professional", viewModel.selectedTone)

        viewModel.updatePersonality("Casual")
        assertEquals("Casual", viewModel.personalityMode)
        assertEquals("Casual", viewModel.selectedTone)
    }
}
