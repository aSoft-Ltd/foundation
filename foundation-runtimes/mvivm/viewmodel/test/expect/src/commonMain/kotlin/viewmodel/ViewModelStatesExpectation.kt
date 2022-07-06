package viewmodel

import expect.BasicExpectation
import kotlin.math.sign
import kotlin.test.assertEquals

interface ViewModelStatesExpectation<S> : BasicExpectation<List<S>> {

    fun toBeIn(state: S) {
        assertEquals(
            state, value.lastOrNull(),
            """
            
        Expected ViewModel State : $state
        Actual ViewModel State   : ${value.lastOrNull()}
        ==================================
        
        """.trimIndent()
        )
    }

    private fun StringBuilder.appendStates(states: Iterable<S>) = states.forEachIndexed { index, s ->
        appendLine("\t${index + 1}. $s")
    }

    private fun expectActualMessage(expecteds: List<S>, actuals: List<S>) = buildString {
        appendLine("State phases didn't match")
        appendLine()
        val diff = expecteds.size - actuals.size
        val zipped = when {
            diff > 0 -> expecteds.zip(actuals + List(diff * diff.sign) { null })
            diff < 0 -> (expecteds + List(diff * diff.sign) { null }).zip(actuals)
            else -> expecteds.zip(actuals)
        }
        for (zx in zipped.indices) {
            val (ex, ax) = zipped[zx]
            appendLine("[Phase ${zx + 1}]" + if (ex != ax) " -> MISMATCH HERE" else "")
            appendLine("\tExpected: ${ex?.toString() ?: "NO EXPECTED STATE"}")
            appendLine("\tActual  : ${ax?.toString() ?: "NO ACTUAL STATE"}")
            appendLine()
        }
    }

    fun toGoThrough(vararg states: S): List<S> {
        assertEquals(
            states.toList().toString(), value.toString(),
            expectActualMessage(states.toList(), value)
        )
        return value
    }
}