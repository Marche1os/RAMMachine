package domain.interpreter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OutputTapeUI(tape: List<String>) {
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