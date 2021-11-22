package terminal

interface Terminal : TerminalOutput {
    fun executeReadLine(prompt: String): String
    fun readln(prompt: String = ""): String {
        val res = executeReadLine(prompt)
        history.push(res)
        return res
    }
}