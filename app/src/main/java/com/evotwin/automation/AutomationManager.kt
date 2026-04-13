package com.evotwin.automation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class AutomationManager(private val context: Context) {

    fun execute(action: String) {
        when {
            action.contains("open", ignoreCase = true) -> {
                val appName = action.substringAfter("open ").trim()
                openApp(appName)
            }
            action.contains("wifi", ignoreCase = true) -> {
                toggleWifi()
            }
            action.contains("message", ignoreCase = true) -> {
                sendMessage(action)
            }
        }
    }

    private fun openApp(name: String) {
        val intent = context.packageManager.getLaunchIntentForPackage(name) // Simplification: assuming package name
        if (intent != null) {
            context.startActivity(intent)
        }
    }

    private fun toggleWifi() {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun sendMessage(action: String) {
        // Mock logic for sending a message via Intent
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:")
            putExtra("sms_body", "EvoTwin Auto-Reply: $action")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
