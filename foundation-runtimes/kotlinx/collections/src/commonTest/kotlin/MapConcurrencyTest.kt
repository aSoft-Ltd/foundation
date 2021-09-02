import expect.*
import kotlinx.atomic.collections.mutableAtomicListOf
import kotlinx.atomic.collections.mutableAtomicMapOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.universal.runTest
import kotlinx.coroutines.withContext
import kotlin.test.Test

class MapConcurrencyTest {
    val numbers: MutableMap<String, Int> = mutableAtomicMapOf()

    @Test
    fun should_add_items_to_list() {
        numbers["one"] = 1
    }

    @Test
    fun should_add_items_in_different_threads() = runTest {
        val chars: MutableMap<String, Char> = mutableAtomicMapOf("A" to 'A')
        withContext(Dispatchers.Default) {
            chars["B"] = 'B'
        }
        withContext(Dispatchers.Unconfined) {
            chars["C"] = 'C'
        }
        expect(chars.values).toContain('A', 'B', 'C')
    }

    @Test
    fun should_add_multiple_items_in_different_threads() = runTest {
        val chars: MutableMap<String, Char> = mutableAtomicMapOf("A" to 'A')
        withContext(Dispatchers.Default) {
            chars.putAll(listOf("B" to 'B'))
        }
        withContext(Dispatchers.Unconfined) {
            chars.putAll(listOf("C" to 'C'))
        }
        expect(chars.values).toContain('A', 'B', 'C')
    }

    @Test
    fun should_remove_items_in_any_thread() = runTest {
        val chars: MutableMap<String, Char> = mutableAtomicMapOf("A" to 'A', "B" to 'B', "C" to 'C')
        withContext(Dispatchers.Default) {
            chars.remove("B")
        }
        withContext(Dispatchers.Unconfined) {
            chars.remove("C")
        }
        expect(chars.values).toContain('A')
        expect(chars.values).toBeOfSize(1)
    }
}