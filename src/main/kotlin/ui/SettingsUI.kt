package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SettingsUI(
    onDismiss: () -> Unit = {},
    onConfirm: (delayMs: Long) -> Unit = {}
) {
    var inputText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .size(300.dp)
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Введите время исполнения команды") },
                placeholder = {
                    Text("Можно добавить через Enter")
                },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        inputText.toLongOrNull()
                            ?.let { onConfirm(it) }
                    }
                )
            )
        }
    }
}