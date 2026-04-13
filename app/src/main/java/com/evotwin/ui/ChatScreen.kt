package com.evotwin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    var input by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(Modifier.weight(1f).fillMaxWidth()) {
            items(viewModel.messages) { message ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    contentAlignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Text(
                        text = message.text,
                        modifier = Modifier
                            .background(
                                color = if (message.isUser) Color(0xFFD1E7FF) else Color(0xFFF1F1F1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (input.isNotBlank()) {
                    viewModel.sendMessage(input)
                    input = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}
