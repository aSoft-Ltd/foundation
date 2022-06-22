package logging

data class Log(
    val level: LogLevel,
    val message: String,
    val metadata: Map<String, Any>
) {
    val source get() = metadata["source"]
}