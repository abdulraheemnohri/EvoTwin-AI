package com.evotwin.automation

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AutomationService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName == "com.whatsapp") {
            val msg = event.text?.joinToString() ?: return
            // Future: AI auto reply injection
        }
    }

    override fun onInterrupt() {}
}