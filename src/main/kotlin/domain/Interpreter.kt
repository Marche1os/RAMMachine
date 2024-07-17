package domain

interface Interpreter {

    /**
     * Читает команду с [InputTape]
     */
    fun readCommand()
}