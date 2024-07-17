package domain

/**
 * Представляет собой выходную ленту целых чисел, в которую команды [domain.commands.Command] записывают значения
 */
interface OutputTape {

    /**
     * Записывает целое число в выходную ленту
     */
    fun write(number: Int)
}