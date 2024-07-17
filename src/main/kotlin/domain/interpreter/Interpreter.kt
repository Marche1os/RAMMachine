package domain.interpreter

interface Interpreter {
    /**
     * Обновляем листинг кода
     */
    fun updateListingCode(code: String)

    /**
     * Читает команду с [InputTape]
     */
    fun readCommand()
}