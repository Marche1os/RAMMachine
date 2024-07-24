package domain

import androidx.compose.runtime.mutableStateListOf
import domain.interpreter.Interpreter
import domain.interpreter.RegToRegInterpreter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Управляющий класс для RAM-машины. Содержит регистры, указатели на команды, листинг кода и статусы программы
 */
class RamMachine {
    private var interpterer: Interpreter = RegToRegInterpreter()
    val executionCommand: MutableStateFlow<String> = MutableStateFlow("")
    val errors: MutableStateFlow<String> = MutableStateFlow("")

    var commandPointer = 0
    var inputPointer = 0

    val input = mutableStateListOf("")
    val registers: MutableMap<Int, String> = mutableMapOf()
    val outputValues = mutableStateListOf<String>()

    var commands: List<String> = emptyList()
    var transitionStory = mutableMapOf<String, String>()
    var isStopped = false
    var delayMs: Long = 1_000
    var scope = CoroutineScope(Dispatchers.Default)

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
        outputValues.clear()
        executionCommand.update { "" }
        isStopped = false
        errors.update { "" }
        scope = CoroutineScope(Dispatchers.Default)
    }

    fun step() {
        if (commands.isEmpty() || isStopped) {
            isStopped = true
            return
        }

        val result = runCatching { interpterer.readCommand(this) }
        result.onFailure {
            isStopped = true
            val currentCommand = commands.getOrNull(commandPointer)

            println(it)

            errors.update {
                "Ошибка выполнения команды: $currentCommand"
            }
        }
    }

    fun run() {
        val scope = CoroutineScope(Dispatchers.Default)
        isStopped = false

        scope.launch {
            while (!isStopped) {
                step()
                delay(delayMs)
            }
        }
    }

    fun stop() {
        isStopped = true
    }

    fun writeSummatorToOutput() {
        outputValues.add(getSummatorValue())
    }

    fun getSummatorValue(): String = registers[0].toString()
}