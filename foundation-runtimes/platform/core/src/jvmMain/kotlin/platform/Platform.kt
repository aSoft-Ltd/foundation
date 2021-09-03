package platform

actual object Platform : ExecutionEnvironment(
    name = Name.JVM.name,
    runner = Runner(
        name = "JRE",
        version = System.getProperty("java.version")
    ),
    os = OperatingSystem(
        family = System.getProperty("os.name"),
        version = System.getProperty("os.version")
    )
)