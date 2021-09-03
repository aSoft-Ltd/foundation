package platform

import android.os.Build

actual object Platform : ExecutionEnvironment(
    name = Name.Android.name,
    runner = Runner(
        name = "ART",
        version = Build.VERSION.RELEASE ?: try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Build.VERSION.RELEASE_OR_CODENAME
            } else {
                "Unknown"
            }
        } catch (err: Throwable) {
            "Unknown"
        }
    ),
    os = OperatingSystem(
        family = Name.Android.name,
        version = Build.VERSION.RELEASE ?: try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Build.VERSION.RELEASE_OR_CODENAME
            } else {
                "Unknown"
            }
        } catch (err: Throwable) {
            "Unknown"
        }
    )
)