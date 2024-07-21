package domain.interpreter

import domain.CommandModel
import domain.RegisterToRegisterCommandModel
import ui.CodeValidState

class InterpreterImpl : Interpreter {
    private var listing: List<String> = emptyList()
    private val commandModel: CommandModel = RegisterToRegisterCommandModel()

    private var currentCommand = 0

    override fun updateListingCode(code: String): CodeValidState {
        listing = code
            .trim()
            .split("\n")

        val isCodeValid = commandModel.checkCommands(listing)

        if (!isCodeValid) {
            return CodeValidState.Invalid
        }

        return CodeValidState.Valid
    }

    override fun readCommand() {
        commandModel.performCommand(listing[currentCommand++])
    }
}