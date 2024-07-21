package domain.interpreter

import ui.CodeValidState

/**
 * Отвечает за чтение кода программы, верификацию кода и выполнение очередной команды
 */
interface Interpreter {
    /**
     * Обновляем листинг кода
     */
    fun updateListingCode(code: String): CodeValidState

    /**
     * Читает команду с [InputTape]
     */
    fun readCommand()
}