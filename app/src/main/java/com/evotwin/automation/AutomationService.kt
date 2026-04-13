package com.evotwin.automation

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AutomationService : AccessibilityService() {
    private lateinit var manager: AutomationManager

    override fun onServiceConnected() {
        manager = AutomationManager(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName == "com.whatsapp") {
            val msg = event.text?.joinToString() ?: return
            // AI auto reply logic would go here
        }
    }

    override fun onInterrupt() {}
}
