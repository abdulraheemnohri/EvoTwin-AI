package com.evotwin.ui

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evotwin.ai.LiteRtEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch {
            statusMessage = "Connecting to Hugging Face..."
            val modelUrl = "https://huggingface.co/litert-community/gemma-4-E2B-it-litert-lm/resolve/main/gemma-4-E2B-it.litertlm?download=true"

            try {
                withContext(Dispatchers.IO) {
                    val file = File(context.filesDir, "gemma-4-E2B-it.litertlm")
                    URL(modelUrl).openStream().use { input ->
                        FileOutputStream(file).use { output ->
                            statusMessage = "Downloading model (2.5GB)..."
                            input.copyTo(output)
                        }
                    }
                    liteRtEngine?.reloadModel()
                }
                isModelLoaded = true
                statusMessage = "Model downloaded and active."
            } catch (e: Exception) {
                statusMessage = "Download failed: ${e.message}"
            }
        }
    }

    fun uploadModel(context: Context, uri: Uri) {
        viewModelScope.launch {
            statusMessage = "Processing model file..."
            try {
                // Request persistable permission just in case, though usually not needed for immediate copy
                try {
                    context.contentResolver.takePersistableUriPermission(uri, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
                } catch (e: Exception) {
                    // Ignore if not supported by the provider
                }

                withContext(Dispatchers.IO) {
                    val destFile = File(context.filesDir, "gemma-4-E2B-it.litertlm")
                    val fileSize = getFileSize(context, uri)

                    context.contentResolver.openInputStream(uri)?.use { input ->
                        FileOutputStream(destFile).use { output ->
                            val buffer = ByteArray(1024 * 1024) // 1MB buffer
                            var bytesRead: Int
                            var totalRead: Long = 0

                            while (input.read(buffer).also { bytesRead = it } != -1) {
                                output.write(buffer, 0, bytesRead)
                                totalRead += bytesRead

                                if (fileSize > 0) {
                                    val progress = (totalRead * 100 / fileSize).toInt()
                                    withContext(Dispatchers.Main) {
                                        statusMessage = "Uploading: $progress%"
                                    }
                                }
                            }
                        }
                    }
                    liteRtEngine?.reloadModel()
                }
                isModelLoaded = true
                statusMessage = "Model uploaded and active."
            } catch (e: Exception) {
                statusMessage = "Upload failed: ${e.message}"
            }
        }
    }

    private fun getFileSize(context: Context, uri: Uri): Long {
        return try {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                cursor.getLong(sizeIndex)
            } ?: 0L
        } catch (e: Exception) {
            0L
        }
    }
}
