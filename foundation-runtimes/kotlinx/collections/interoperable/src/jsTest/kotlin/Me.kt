external interface Me {
    val name: String
    val greet: () -> Unit
    fun <T> doStuff(array: Array<T>): Array<T>
    fun <T> doStuff(list: List<T>): Array<T>
    fun <T> convertToArray(list: List<T>): Array<T>
    fun addUnbounded(calc: Calc, number: Int): Int
}