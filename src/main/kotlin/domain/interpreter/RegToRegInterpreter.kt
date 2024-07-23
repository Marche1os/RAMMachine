package domain.interpreter

import androidx.compose.ui.util.fastJoinToString
import domain.RamMachine
import kotlinx.coroutines.flow.update

class RegToRegInterpreter : Interpreter {

    override fun readCommand(ramMachine: RamMachine) {
        if (ramMachine.commandPointer >= ramMachine.commands.size) {
            return
        }

        val currentCommand = ramMachine.commands[ramMachine.commandPointer++]
        ramMachine.executionCommand.update { currentCommand }

        if (currentCommand.startsWith("WRITE")) {
            ramMachine.writeSummatorToOutput()
        } else if (currentCommand.startsWith("READ")) {
            if (ramMachine.inputPointer + 1 >= ramMachine.input.size) {
                return
            }
            ramMachine.inputPointer++

            ramMachine.input[0] = ramMachine.input[ramMachine.inputPointer]
        } else if (currentCommand.startsWith("LOAD")) {
            val args = currentCommand.substring(4).trim()
            val argValues = args.split(",")

            val value = getValue(argValues.first(), ramMachine.input.toList())
            val address = getAddress(argValues.last().trim(), ramMachine.input.toList())

            ramMachine.input[address] = value
        } else if (currentCommand.startsWith("ADD")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val value1 = getValue(argValues[0].trim(), ramMachine.input.toList()).toInt()
            val value2 = getValue(argValues[1].trim(), ramMachine.input.toList()).toInt()
            val address = getAddress(argValues[2].trim(), ramMachine.input.toList())

            ramMachine.input[address] = (value1 + value2).toString()
        } else if (currentCommand.startsWith("SUB")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val value1 = getValue(argValues[0].trim(), ramMachine.input.toList()).toInt()
            val value2 = getValue(argValues[1].trim(), ramMachine.input.toList()).toInt()
            val address = getAddress(argValues[2].trim(), ramMachine.input.toList())

            ramMachine.input[address] = (value1 - value2).toString()
        } else if (currentCommand.startsWith("CPY")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val address1 = getAddress(argValues.first().trim(), ramMachine.input.toList())
            val address2 = getAddress(argValues.last().trim(), ramMachine.input.toList())
            ramMachine.input[address2] = ramMachine.input[address1]
        } else if (currentCommand.startsWith("JNZ")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            println(argValues)

            val value = getValue(argValues.first().trim(), ramMachine.input.toList()).toInt()
            val label = argValues.last().trim()

            if (value > 0) {
                val commands = ramMachine.commands
                commands.forEachIndexed { index, command ->
                    if (command.contains("$label:")) {
                        val currentStateOfRegisters = ramMachine.input.toList().fastJoinToString()

                        if (ramMachine.transitionStory.contains(label)) {
                            val registerValue = ramMachine.transitionStory.getValue(label)
                            if (registerValue == currentStateOfRegisters) {
                                throw IllegalStateException("Программа попала в состояние deadlock и будет завершена!")
                            }
                        }

                        ramMachine.transitionStory[label] = currentStateOfRegisters
                        ramMachine.commandPointer = index
                        return
                    }
                }
            }
        }
    }

    private fun getAddress(value: String, input: List<String>): Int {
        if (value.startsWith("[[") && value.endsWith("]]")) {
            val realValue = value.substring(2)
            val link = realValue.substring(0, realValue.length - 2).toInt()
            val address = input[link].toInt()

            return address
        }

        if (value.startsWith("[") && value.endsWith("]")) {
            val value1 = value.substring(1)
            val address = value1.substring(0, value1.length - 1).toInt()
            return address
        }

        return value.toInt()
    }

    private fun getValue(value: String, input: List<String>): String {
        if (value.startsWith("[[") && value.endsWith("]]")) {
            val realValue = value.substring(2)
            val link = realValue.substring(0, realValue.length - 2).toInt()
            val address = input[link].toInt()

            return input[address]
        }

        if (value.startsWith("[") && value.endsWith("]")) {
            val value1 = value.substring(1)
            val address = value1.substring(0, value1.length - 1).toInt()
            return input[address]
        }

        return value
    }
}