package later

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Later<T>.asFlow(): Flow<T> = flow {
    emit(await())
}