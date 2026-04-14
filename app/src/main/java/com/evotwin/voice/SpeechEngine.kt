package com.evotwin.voice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

class SpeechEngine(private val context: Context, private val onResult: (String) -> Unit) {
    private val recognizer = SpeechRecognizer.createSpeechRecognizer(context)
    private var isContinuousListening = false

    fun startContinuous() {
        isContinuousListening = true
        listen()
    }

    fun stopContinuous() {
        isContinuousListening = false
        recognizer.stopListening()
    }

    fun listen() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                data?.firstOrNull()?.let {
                    onResult(it)
                    if (isContinuousListening) listen() // Recurse for continuous
                }
            }
            override fun onError(error: Int) {
                if (isContinuousListening) listen() // Retry on error if continuous
            }
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
        recognizer.startListening(intent)
    }
}
