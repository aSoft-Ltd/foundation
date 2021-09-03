package platform

actual object Platform : ExecutionEnvironment(
    name = Name.iOS.name,
    runner = Runner(
        name = Name.iOS.name,
        version = "Unknown"
    ),
    os = OperatingSystem(
        family = Name.iOS.name,
        version = "Unknown"
    )
)