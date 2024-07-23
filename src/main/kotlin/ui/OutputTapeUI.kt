package ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OutputTapeUI(tape: List<String>) {
    OutlinedTextField(
        modifier = Modifier
            .height(180.dp)
            .padding(horizontal = 16.dp)
            .width(150.dp),
        value = tape.joinToString(separator = "\n"),
        enabled = false,
        onValueChange = {},
        label = {
            Text(text = "Результат")
        }
    )

//    Row(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        tape.forEachIndexed { index, value ->
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(text = "$index")
//
//                Card(
//                    modifier = Modifier
//                        .size(64.dp),
//                    border = BorderStroke(2.dp, Color.LightGray),
//                ) {
//                    Text(
//                        value,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.padding(8.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.width(8.dp))
//        }
//    }
}