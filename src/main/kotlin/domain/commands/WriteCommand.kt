package domain.commands

class WriteCommand : Command() {

    override fun perform() {
        println("Write command")
    }
}