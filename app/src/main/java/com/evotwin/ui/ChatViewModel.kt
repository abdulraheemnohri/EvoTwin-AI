package com.evotwin.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evotwin.ai.AiEngine
import kotlinx.coroutines.launch

class ChatViewModel(private val aiEngine: AiEngine) : ViewModel() {
    val messages = mutableStateListOf<ChatMessage>()

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        messages.add(ChatMessage(text, isUser = true))

        viewModelScope.launch {
            val response = aiEngine.chat(text)
            messages.add(ChatMessage(response, isUser = false))
        }
    }
}

data class ChatMessage(val text: String, val isUser: Boolean)
