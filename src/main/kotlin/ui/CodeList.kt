package ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CodeList(
    onTextChanged: (text: String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
//            .heightIn(min = 120.dp, max = 160.dp)
            .height(180.dp)
            .padding(16.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { newText ->
            text = newText
            onTextChanged(text)
        },
        label = { Text(text = "Код программы") }
    )
}