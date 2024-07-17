package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InputTapeUI(
    onTapeChanged: (tape: List<String>) -> Unit,
) {
    var inputText by remember { mutableStateOf("") }
    val tape = remember { mutableStateListOf("0") }

    val addValueAction: (input: String) -> Unit = { input ->
        if (input.toIntOrNull() != null) {
            tape.add(input)
            inputText = ""

            onTapeChanged(tape.toList())
        }
    }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Введите число") },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        addValueAction(inputText)
                    }
                )
            )

            Button(
                onClick = {
                    addValueAction(inputText)
                },
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text("Добавить")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Входная лента:", modifier = Modifier.padding(horizontal = 16.dp))

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            tape.forEachIndexed { index, value ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$index")

                    Card(
                        modifier = Modifier
                            .size(64.dp),
                        border = BorderStroke(2.dp, Color.LightGray),
                    ) {
                        Text(
                            value,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}