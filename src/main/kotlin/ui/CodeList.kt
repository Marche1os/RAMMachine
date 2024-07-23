package ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CodeList(
    code: String,
    onTextChanged: (text: String) -> Unit,
) {

    OutlinedTextField(
        modifier = Modifier
            .height(180.dp)
            .padding(horizontal = 16.dp)
            .width(300.dp),
        value = code,
        onValueChange = { newText ->
            onTextChanged(newText)
        },
        label = { Text(text = "Код программы") },
    )
}