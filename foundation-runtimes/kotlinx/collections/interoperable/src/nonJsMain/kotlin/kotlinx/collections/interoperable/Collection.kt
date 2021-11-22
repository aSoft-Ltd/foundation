package kotlinx.collections.interoperable

import kotlin.collections.Collection as KCollection

actual interface Collection<out E> : Iterable<E>, KCollection<E>