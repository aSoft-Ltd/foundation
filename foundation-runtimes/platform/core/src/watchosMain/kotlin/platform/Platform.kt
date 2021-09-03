package platform

actual object Platform : ExecutionEnvironment(
    name = Name.WatchOS.name,
    runner = Runner(
        name = Name.WatchOS.name,
        version = "Unknown"
    ),
    os = OperatingSystem(
        family = Name.WatchOS.name,
        version = "Unknown"
    )
)