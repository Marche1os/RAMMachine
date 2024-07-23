package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
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
    var error by remember { mutableStateOf("") }

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
                isError = error.isNotBlank(),
                onValueChange = { inputText = it },
                label = { Text("Время в м/с") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            )

            Button(
                onClick = {
                    inputText.toLongOrNull()
                        ?.let { onConfirm(it) }
                },
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text("Добавить")
            }

            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text("Отмена")
            }
        }
    }
}