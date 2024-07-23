package domain

import androidx.compose.runtime.mutableStateListOf
import domain.interpreter.Interpreter
import domain.interpreter.RegToRegInterpreter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RamMachine {
    private var interpterer: Interpreter = RegToRegInterpreter()
    val executionCommand: MutableStateFlow<String> = MutableStateFlow("")

    var commandPointer = 0
    var inputPointer = 0

    val input = mutableStateListOf("0")
    val outputValues = mutableStateListOf<String>()

    var commands: List<String> = emptyList()
    var transitionStory = mutableMapOf<String, String>()

    fun setCode(code: String) {
        commands = code
            .trim()
            .reader()
            .readLines()
    }

    /**
     * Очищает состояние программы, начать все заново
     */
    fun restart() {
        inputPointer = 0
        commandPointer = 0
        commands = emptyList()
        transitionStory = mutableMapOf()
        input.clear()
        input.add("0")
        outputValues.clear()
        executionCommand.update { "" }
    }

    fun step() {
        if (commands.isEmpty()) return

        interpterer.readCommand(this)
    }

    fun run() {

    }

    fun stop() {

    }

    fun writeSummatorToOutput() {
        outputValues.add(getSummatorValue())
    }


    fun getSummatorValue(): String = input.first()
}