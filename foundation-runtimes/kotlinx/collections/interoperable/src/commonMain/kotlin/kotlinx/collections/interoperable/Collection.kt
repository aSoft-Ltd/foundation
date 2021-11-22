package kotlinx.collections.interoperable

import kotlin.collections.Collection as KCollection

expect interface Collection<out E> : Iterable<E>, KCollection<E>