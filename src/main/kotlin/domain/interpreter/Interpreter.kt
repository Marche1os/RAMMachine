package domain.interpreter

import domain.RamMachine

/**
 * Отвечает за чтение кода программы, верификацию кода и выполнение очередной команды
 */
interface Interpreter {
    /**
     * Читает команду с [InputTape]
     */
    fun readCommand(ramMachine: RamMachine)
}