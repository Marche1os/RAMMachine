package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.interpreter.Interpreter
import domain.interpreter.InterpreterImpl

@Composable
fun RamMachineUI() {
    val interpreter: Interpreter = InterpreterImpl()
    var isCodeValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        MenuUI(
            onStepClick = {
                interpreter.readCommand()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        CodeList {
            interpreter.updateListingCode(it)
        }

        Spacer(modifier = Modifier.height(12.dp))

        InputTapeUI { println(it) }
    }
}
