package domain

import domain.commands.*

class RegisterToRegisterCommandModel : CommandModel {
    private val commands = mapOf(
        "READ" to ReadCommand(),
        "LOAD" to LoadCommand(),
        "WRITE" to WriteCommand(),
        "CPY" to CopyCommand(),
        "SUB" to SubCommand(),
        "HALT" to HaltCommand(),
    )

    override fun checkCommands(code: List<String>): Boolean {
        val codeCommands = code.map {
            it.split(" ")[0]
        }

        return commands.keys.containsAll(codeCommands)
    }
}