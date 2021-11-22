package kotlinx.collections.interoperable

import kotlin.collections.Iterable as KIterable

expect interface Iterable<out E> : KIterable<E>