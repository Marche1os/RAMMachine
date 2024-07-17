package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RamMachineUI() {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MenuUI()

        Spacer(modifier = Modifier.height(12.dp))

        CodeList {
            println(it)
        }

        Spacer(modifier = Modifier.height(12.dp))

        InputTapeUI { println(it) }
    }
}
