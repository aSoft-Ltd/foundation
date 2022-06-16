package koncurrent

/**
 * ### State of a [Later]
 * An encapsulation representing a [Later.state]
 *
 * A [Later] always begin on a [Pending] state.
 *
 * When it runs, and finishes off it's work it gets [Settled]
 * in either a [FULFILLED] or [REJECTED] state
 */
sealed class ConcurrentState<out T>

/**
 * Initial [ConcurrentState]. Every later begins at this state
 */
object PendingState : ConcurrentState<Nothing>() {
    override fun toString() = "PENDING"
}

/**
 * Encapsulation of a settled stated.
 *
 * This may be either [Fulfilled] or [Rejected]
 */
sealed class Settled<out T> : ConcurrentState<T>()

/**
 * A state representing a successful completion of a [Later]
 * @param value The value that this [Later] completed with
 */
data class Fulfilled<out T>(val value: T) : Settled<T>() {
    override fun toString(): String = "Fulfilled($value)"
}

/**
 * A state representing a failed completion of a [Later]
 * @param cause The [Throwable] that caused the failure
 */
data class Rejected(val cause: Throwable) : Settled<Nothing>() {
    override fun toString(): String = "Rejected('${cause.message}')"
}

