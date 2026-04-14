package com.evotwin.ai

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import org.tensorflow.lite.Interpreter

class LiteRtEngine(private val context: Context) {
    private var interpreter: Interpreter? = null
    private val modelFileName = "gemma-4-E2B-it.litertlm"

    init {
        val storageModel = File(context.filesDir, modelFileName)
        if (storageModel.exists()) {
            loadFromStorage(storageModel)
        } else {
            try {
                loadFromAssets(modelFileName)
            } catch (e: Exception) {
                println("LiteRtEngine: No model found in storage or assets.")
            }
        }
    }

    private fun loadFromStorage(file: File) {
        val fis = FileInputStream(file)
        val buffer = fis.channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length())
        interpreter = Interpreter(buffer)
    }

    private fun loadFromAssets(name: String) {
        val fileDescriptor = context.assets.openFd(name)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val buffer = inputStream.channel.map(
            FileChannel.MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )
        interpreter = Interpreter(buffer)
    }

    fun generate(prompt: String): String {
        if (interpreter == null) return "AI Model not loaded. Please download or upload in settings."

        val input = arrayOf(prompt)
        val output = Array(1) { ByteArray(2048) }
        interpreter?.run(input, output)
        return String(output[0]).trim()
    }
}
