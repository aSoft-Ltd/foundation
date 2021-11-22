package unit

import expect.expect
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.mutableListOf
import kotlin.test.Test

class ListTest {
    @Test
    fun should_be_instantiatable() {
        val list = listOf(1, 2, 3, 4, 5)
        println(list)
        expect(list).toContain(2, 3, 4)
    }

    @Test
    fun mutable_lists_should_be_like_lists() {
        val mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7)
        println(mutableList)
        expect(mutableList).toContain(2, 5, 7)
    }

    @Test
    fun empty_list_prints_correctly() {
        val emptyList = emptyList()
        println(emptyList)
        expect(emptyList.isEmpty()).toBe(true)
    }
}