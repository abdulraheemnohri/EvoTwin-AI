package com.evotwin.ai

import android.content.Context
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import org.tensorflow.lite.Interpreter

class LiteRtEngine(private val context: Context) {
    private val interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModel("gemma-4-E2B-it.litertlm"))
    }

    private fun loadModel(name: String): MappedByteBuffer {
        val file = context.assets.openFd(name)
        val input = file.createInputStream()
        return input.channel.map(
            FileChannel.MapMode.READ_ONLY,
            file.startOffset,
            file.declaredLength
        )
    }

    fun generate(prompt: String): String {
        val input = arrayOf(prompt)
        val output = Array(1) { ByteArray(2048) }
        interpreter.run(input, output)
        return String(output[0]).trim()
    }
}