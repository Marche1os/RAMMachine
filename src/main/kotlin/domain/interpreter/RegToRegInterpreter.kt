package domain.interpreter

import androidx.compose.ui.util.fastJoinToString
import domain.RamMachine
import kotlinx.coroutines.flow.update

class RegToRegInterpreter : Interpreter {

    override fun readCommand(ramMachine: RamMachine) {
        val currentCommand = ramMachine.commands[ramMachine.commandPointer++]
        ramMachine.executionCommand.update { currentCommand }

        if (currentCommand.startsWith("WRITE")) {
            ramMachine.writeSummatorToOutput()
        } else if (currentCommand.startsWith("READ")) {
            val value = ramMachine.input.getOrNull(ramMachine.inputPointer)

            if (value == null) {
                ramMachine.registers[0] = "-128"
            } else {
                ramMachine.registers[0] = value.toString()
                ramMachine.inputPointer++
            }

//            ramMachine.input[0] = ramMachine.input[ramMachine.inputPointer]
        } else if (currentCommand.startsWith("LOAD")) {
            val args = currentCommand.substring(4).trim()
            val argValues = args.split(",")

            val value = calcValue(argValues.first().trim(), ramMachine.registers)
            val address = calcIndex(argValues.last().trim(), ramMachine.registers)

            ramMachine.registers[address] = value
        } else if (currentCommand.startsWith("ADD")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val value1 = calcValue(argValues[0].trim(), ramMachine.registers).toInt()
            val value2 = calcValue(argValues[1].trim(), ramMachine.registers).toInt()
            val address = calcIndex(argValues[2].trim(), ramMachine.registers)

            ramMachine.registers[address] = (value1 + value2).toString()
        } else if (currentCommand.startsWith("SUB")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val value1 = calcValue(argValues[0].trim(), ramMachine.registers).toInt()
            val value2 = calcValue(argValues[1].trim(), ramMachine.registers).toInt()
            val address = calcIndex(argValues[2].trim(), ramMachine.registers)

            val diff = value1 - value2
            var result: Int = if (diff > 127) 127 else diff
            result = if (result < -128) -128 else result
            ramMachine.registers[address] = result.toString()

//            ramMachine.input[address] = (value1 - value2).toString()
        } else if (currentCommand.startsWith("CPY")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val address1 = calcIndex(argValues.first().trim(), ramMachine.registers)
            val address2 = calcIndex(argValues.last().trim(), ramMachine.registers)
            ramMachine.registers[address2] = ramMachine.registers.getValue(address1)
        } else if (currentCommand.startsWith("JNZ")) {
            val args = currentCommand.substring(3).trim()
            val argValues = args.split(",")

            val labelValue = calcValue(argValues.first().trim(), ramMachine.registers).toInt()
            val label = argValues[1].trim()

            if (labelValue > 0) {
                val commands = ramMachine.commands
                commands.forEachIndexed { index, command ->
                    if (command.contains("$label:")) {
                        val currentStateOfRegisters = ramMachine.registers.values.toList().fastJoinToString()

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
        } else if (currentCommand.startsWith("HALT")) {
            ramMachine.commandPointer--
            ramMachine.isStopped = true
        }
    }

    /**
     * На основе типа адресации (косвенная, прямая, нонстантная) вычисляем значение индекс ленты
     */
    private fun calcIndex(value: String, registers: Map<Int, String>): Int {
        if (value.startsWith("[[") && value.endsWith("]]")) {
            val realValue = value.substring(2)
            val link = realValue.substring(0, realValue.length - 2).toInt()
            val address = registers.getValue(link).toInt()

            return address
        }

        if (value.startsWith("[") && value.endsWith("]")) {
            val value1 = value.substring(1)
            val address = value1.substring(0, value1.length - 1).toInt()
            return address
        }

        return value.toInt()
    }

    /**
     * На основе типа адресации (косвенная, прямая, нонстантная) вычисляем значение ленты
     */
    private fun calcValue(value: String, registers: Map<Int, String>): String {
        if (value.startsWith("[[") && value.endsWith("]]")) {
            val realValue = value.substring(2)
            val link = realValue.substring(0, realValue.length - 2).toInt()
            val address = registers[link]?.toInt()

            return registers[address].toString()
        }

        if (value.startsWith("[") && value.endsWith("]")) {
            val value1 = value.substring(1)
            val address = value1.substring(0, value1.length - 1).toInt()
            return registers[address].toString()
        }

        return value
    }
}