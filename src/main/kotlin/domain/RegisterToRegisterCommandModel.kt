package domain

import domain.commands.*

class RegisterToRegisterCommandModel(override var inputTape: MutableList<String>) : CommandModel {
    private val commands = mapOf(
        "READ" to ReadCommand(),
        "LOAD" to LoadCommand(),
        "WRITE" to WriteCommand(),
        "CPY" to CopyCommand(),
        "SUB" to SubCommand(),
        "HALT" to HaltCommand(),
    )

    init {
        commands.forEach {

        }
    }

    override fun checkCommands(code: List<String>): Boolean {
        val codeCommands = code.map {
            it.split(" ")[0]
        }

        return commands.keys.containsAll(codeCommands)
    }

    override fun performCommand(command: String) {
        commands.getValue(command).perform()
    }
}