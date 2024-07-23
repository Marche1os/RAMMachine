package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.RamMachine

val ramMachine = RamMachine()

@Composable
fun RamMachineUI() {
    var isCodeValid by remember { mutableStateOf(true) }
    val tapeValues = ramMachine.input

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MenuUI(
            onStepClick = {
                ramMachine.step()
            },
            isEnableMenu = isCodeValid,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CodeList {
            ramMachine.setCode(it)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Текущая команда: ${ramMachine.executionCommand.collectAsState().value}"
        )

        Spacer(modifier = Modifier.height(12.dp))

        InputTapeUI(tapeValues) { println(tapeValues.toList()) }

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Выходная лента")

        OutputTapeUI(ramMachine.outputValues)
    }
}
