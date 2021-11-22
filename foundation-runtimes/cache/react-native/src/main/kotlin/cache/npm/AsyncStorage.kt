@file:JsModule("@react-native-async-storage/async-storage")
@file:JsNonModule

package cache.npm

import kotlin.js.Promise

external interface AsyncStorageStatic {
    fun getAllKeys(): Promise<Array<String>>
    fun setItem(key: String, value: String): Promise<Unit>
    fun getItem(key: String): Promise<String?>
}

@JsName("default")
external val AsyncStorage: AsyncStorageStatic