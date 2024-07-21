package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuUI(
    onNewClick: () -> Unit = {},
    onStepClick: () -> Unit = {},
    onRunClick: () -> Unit = {},
    onStopClick: () -> Unit = {},
    isEnableMenu: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.width(16.dp))

        Button(
            onClick = onNewClick,
        ) {
            Text(text = "New")
        }

        Spacer(Modifier.width(8.dp))

        Button(
            onClick = onStepClick,
            enabled = isEnableMenu,
        ) {
            Text(text = "Step")
        }

        Spacer(Modifier.width(8.dp))

        Button(
            onClick = onRunClick,
            enabled = isEnableMenu,
        ) {
            Text(text = "Run")
        }

        Spacer(Modifier.width(8.dp))

        Button(
            onClick = onStopClick,
            enabled = isEnableMenu,
        ) {
            Text(text = "Stop")
        }
    }
}