package domain

import domain.interpreter.Interpreter
import domain.interpreter.InterpreterImpl

/**
 * Представляет собой выбор меню:
 * - Начать заново
 * - Выполнить шаг программы
 * - Запустить программу на выполнение
 * - Остановить работающую программу
 */
class Controller {
    private val interpreter: Interpreter = InterpreterImpl()

    private var listing: List<String> = emptyList()

    fun updateListingCode(newList: List<String>) {
        listing = newList
    }

    fun restart() {
        listing = emptyList()
    }

    fun performStep() {

    }

    fun run() {

    }

    fun stop() {

    }
}