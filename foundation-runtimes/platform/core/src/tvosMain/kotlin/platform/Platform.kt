package platform

actual object Platform : ExecutionEnvironment(
    name = Name.TvOS.name,
    runner = Runner(
        name = Name.TvOS.name,
        version = "Unknown"
    ),
    os = OperatingSystem(
        family = Name.TvOS.name,
        version = "Unknown"
    )
)