package platform

actual object Platform : ExecutionEnvironment(
    name = Name.MacOS.name,
    runner = Runner(
        name = Name.MacOS.name,
        version = "Unknown"
    ),
    os = OperatingSystem(
        family = Name.MacOS.name,
        version = "Unknown"
    )
)