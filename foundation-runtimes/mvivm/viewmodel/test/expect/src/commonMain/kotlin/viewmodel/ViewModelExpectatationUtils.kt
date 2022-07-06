package viewmodel

import kotlin.test.assertTrue

inline fun <reified E> ViewModelExpectation<*, *>.toBeIn(): E {
    val state = value.ui.value
    assertTrue(
        """
            
        Expected State : ${E::class.simpleName}
        Actual State   : $state
        ==================================
        
        """.trimIndent()
    ) { state is E }
    return state as E
}

inline fun <reified L, reified R> ViewModelExpectation<*, *>.toBeInEither(): Boolean {
    val state = value.ui.value
    assertTrue(
        """
    
    Expected States : [${L::class.simpleName} | ${R::class.simpleName}]
    Actual State    : $state
    =============================================================

    """.trimIndent()
    ) { state is L || state is R }
    return true
}

inline fun <reified E> ViewModelStatesExpectation<*>.toBeIn(): E {
    val state = value.last()
    assertTrue(
        """
            
        Expected State : ${E::class.simpleName}
        Actual State   : $state
        ==================================
        
        """.trimIndent()
    ) { state is E }
    return state as E
}

inline fun <reified L, reified R> ViewModelStatesExpectation<*>.toBeInEither(): Boolean {
    val state = value.last()
    assertTrue(
        """
    
    Expected States : [${L::class.simpleName} | ${R::class.simpleName}]
    Actual State    : ${state!!::class.simpleName}
    =============================================================

    """.trimIndent()
    ) { state is L || state is R }
    return true
}