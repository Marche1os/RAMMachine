package domain

/**
 * Интерфейс, представляющий набор команд.
 *
 * Если требуется новый набор команд, нужно унаследовать [CommandModel] и задать валидные команды для этого набора команд
 */
interface CommandModel {
    var inputTape: MutableList<String>

    /**
     * Проверка, что введенные пользователем команды соответствуют выбранному набору команд
     *
     * @param code - листинг кода, введенный пользователем.
     *
     * @return true, если команды валидны для этого набора команд. Иначе - false
     */
    fun checkCommands(code: List<String>): Boolean

    fun performCommand(command: String)
}