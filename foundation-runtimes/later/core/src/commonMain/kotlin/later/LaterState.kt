package later

import later.LaterState.PENDING
import later.LaterState.Settled
import later.LaterState.Settled.FULFILLED
import later.LaterState.Settled.REJECTED

/**
 * ### State of a [Later]
 * An encapsulation representing a [Later.state]
 *
 * A [Later] always begin on a [PENDING] state.
 *
 * When it runs, and finishes off it's work it gets [Settled]
 * in either a [FULFILLED] or [REJECTED] state
 */
sealed class LaterState<out T> {

    /**
     * Initial [LaterState]. Every later begins at this state
     */
    class PENDING : LaterState<Nothing>() {
        override fun toString() = "PENDING"
    }

    /**
     * Encapsulation of a settled stated.
     *
     * This may be either [FULFILLED] or [REJECTED]
     */
    sealed class Settled<out T> : LaterState<T>() {
        /**
         * A state representing a successful completion of a [Later]
         * @param value The value that this [Later] completed with
         */
        data class FULFILLED<out T>(val value: T) : Settled<T>()

        /**
         * A state representing a failed completion of a [Later]
         * @param cause The [Throwable] that caused the failure
         */
        data class REJECTED(val cause: Throwable) : Settled<Nothing>()
    }
}
