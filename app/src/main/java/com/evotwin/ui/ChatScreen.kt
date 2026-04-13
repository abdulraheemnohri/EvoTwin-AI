package com.evotwin.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen() {
    var input by remember { mutableStateOf("") }
    var chat by remember { mutableStateOf(listOf<String>()) }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.weight(1f)) {
            chat.forEach {
                Text(it)
            }
        }

        Row {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f)
            )

            Button(onClick = {
                chat = chat + "User: $input" + "AI: (response)"
                input = ""
            }) {
                Text("Send")
            }
        }
    }
}