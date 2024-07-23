package domain.commands

/**
 * Классы конкретной команды. Является корневым для всех команд
 */
abstract class Command {

    protected val outputTape = mutableListOf<Int>()

    abstract fun perform()
}