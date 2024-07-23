package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import domain.RamMachine

val ramMachine = RamMachine()

@Composable
fun RamMachineUI() {
    var isCodeValid by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("") }
    val tapeValues = ramMachine.input
    val errors = ramMachine.errors.collectAsState().value
    var isSettingsOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MenuUI(
            onStepClick = {
                ramMachine.step()
            },
            onNewClick = {
                text = ""
                ramMachine.restart()
            },
            onRunClick = {
                ramMachine.run()
            },
            onStopClick = {
                ramMachine.stop()
            },
            onSettingsClick = {
                isSettingsOpen = true
            },
            isEnableMenu = isCodeValid,
        )

        if (isSettingsOpen) {
            SettingsUI(
                onDismiss = { isSettingsOpen = false },
                onConfirm = { delayMs ->
                    ramMachine.delayMs = delayMs
                    isSettingsOpen = false
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            CodeList(text) {
                text = it
                ramMachine.setCode(text)
            }

            Spacer(modifier = Modifier.width(12.dp))

            OutputTapeUI(ramMachine.outputValues)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Текущая команда: ${ramMachine.executionCommand.collectAsState().value}"
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (errors.isNotBlank()) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Red,
                text = "Ошибка! $errors"
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        InputTapeUI(tapeValues) { println(tapeValues.toList()) }

        Spacer(modifier = Modifier.height(12.dp))

    }
}
