package domain.interpreter

import domain.CommandModel
import domain.RegisterToRegisterCommandModel

class InterpreterImpl : Interpreter {
    private var listing: List<String> = emptyList()
    private val commandModel: CommandModel = RegisterToRegisterCommandModel()

    override fun updateListingCode(code: String) {
        listing = code.split("\n")
    }

    override fun readCommand() {
        TODO("Not yet implemented")
    }
}