package terminal

class T {

    sealed class State {
        data class Ready(val prompt: String = "~") : State()
        data class Busy(val message: String = "Loading") : State()
    }

    val commandHistory = mutableListOf<String>()

    fun send(command: String) {

    }

    fun onCommand()
}