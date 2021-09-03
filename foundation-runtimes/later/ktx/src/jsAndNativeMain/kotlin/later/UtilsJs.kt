package later

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
actual val LATER_SCOPE: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)