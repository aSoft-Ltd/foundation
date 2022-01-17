import logging.ConsoleAppender
import logging.LogLevel
import logging.console
import kotlin.test.Test

class ConsoleAppenderTest {
    @Test
    fun api_should_look_good() {
        console.info("Testing again", "first" to 1, "second" to 2)
        console.error("This is a test error", "correct" to false)
    }

    @Test
    fun should_print_less_verbose_errors() {
        val logger = ConsoleAppender(verbose = false)
        logger.debug("Debug test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.failure("Failure test")
    }

    @Test
    fun should_print_by_levels() {
        val logger = ConsoleAppender(level = LogLevel.ERROR)
        logger.debug("Debug test")
        logger.info("Info test")
        logger.warn("Warning test")
        logger.error("Error test")
        logger.failure("Failure test")
    }
}