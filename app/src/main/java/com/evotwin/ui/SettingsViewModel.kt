package com.evotwin.ui

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.evotwin.ai.LiteRtEngine
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class SettingsViewModel(private val liteRtEngine: LiteRtEngine?) : ViewModel() {
    var selectedTone by mutableStateOf("Balanced")
    var voiceCloningEnabled by mutableStateOf(true)
    var importanceThreshold by mutableFloatStateOf(50f)
    var personalityMode by mutableStateOf("Casual")
    var isModelLoaded by mutableStateOf(false)
    var backgroundAgentEnabled by mutableStateOf(false)
    var statusMessage by mutableStateOf("")

    fun updatePersonality(mode: String) {
        personalityMode = mode
        when (mode) {
            "Work" -> selectedTone = "Professional"
            "Casual" -> selectedTone = "Casual"
            "Business" -> selectedTone = "Professional"
        }
    }

    fun downloadModel(context: Context) {
        statusMessage = "Downloading model..."
        val modelUrl = "https://huggingface.co/litert-community/gemma-4-E2B-it-litert-lm/resolve/main/gemma-4-E2B-it.litertlm?download=true"

        Thread {
            try {
                val file = File(context.filesDir, "gemma-4-E2B-it.litertlm")
                URL(modelUrl).openStream().use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
                liteRtEngine?.reloadModel()
                isModelLoaded = true
                statusMessage = "Download complete. Model active."
            } catch (e: Exception) {
                statusMessage = "Download failed: ${e.message}"
            }
        }.start()
    }

    fun uploadModel(context: Context, sourceFile: File) {
        statusMessage = "Uploading model..."
        try {
            val destFile = File(context.filesDir, "gemma-4-E2B-it.litertlm")
            sourceFile.inputStream().use { input ->
                destFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            liteRtEngine?.reloadModel()
            isModelLoaded = true
            statusMessage = "Upload complete. Model active."
        } catch (e: Exception) {
            statusMessage = "Upload failed: ${e.message}"
        }
    }
}
