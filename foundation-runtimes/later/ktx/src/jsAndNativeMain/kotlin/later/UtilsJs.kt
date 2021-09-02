package later

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.universal.Dispatchers
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
actual val LATER_SCOPE: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Universal)