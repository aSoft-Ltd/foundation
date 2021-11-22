package kotlinx.collections.interoperable

import kotlin.collections.Iterable as KIterable

actual interface Iterable<out E> : KIterable<E>