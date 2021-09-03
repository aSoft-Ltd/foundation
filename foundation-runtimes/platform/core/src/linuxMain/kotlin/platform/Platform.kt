package platform

actual object Platform : ExecutionEnvironment(
    name = Name.Linux.name,
    runner = Runner(
        name = Name.Linux.name,
        version = "Unknown"
    ),
    os = OperatingSystem(
        family = Name.Linux.name,
        version = "Unknown"
    )
)