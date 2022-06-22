package kuest

public interface Headers : StringValues {
    public companion object {
        /**
         * Empty [Headers] instance
         */
        @Suppress("DEPRECATION_ERROR")
        public val Empty: Headers = EmptyHeaders

        /**
         * Builds a [Headers] instance with the given [builder] function
         * @param builder specifies a function to build a map
         */
        public inline fun build(builder: HeadersBuilder.() -> Unit): Headers = HeadersBuilder().apply(builder).build()
    }
}

@Suppress("KDocMissingDocumentation")
public class HeadersBuilder(size: Int = 8) : StringValuesBuilderImpl(true, size) {
    override fun build(): Headers {
        return HeadersImpl(values)
    }

    override fun validateName(name: String) {
        super.validateName(name)
        HttpHeaders.checkHeaderName(name)
    }

    override fun validateValue(value: String) {
        super.validateValue(value)
        HttpHeaders.checkHeaderValue(value)
    }
}

internal object EmptyHeaders : Headers {
    override val caseInsensitiveName: Boolean get() = true
    override fun getAll(name: String): List<String>? = null
    override fun names(): Set<String> = emptySet()
    override fun entries(): Set<Map.Entry<String, List<String>>> = emptySet()
    override fun isEmpty(): Boolean = true
    override fun toString(): String = "Headers ${entries()}"
}

/**
 * Returns empty headers
 */
public fun headersOf(): Headers = Headers.Empty

/**
 * Returns [Headers] instance containing only one header with the specified [name] and [value]
 */
public fun headersOf(name: String, value: String): Headers = HeadersSingleImpl(name, listOf(value))

/**
 * Returns [Headers] instance containing only one header with the specified [name] and [values]
 */
public fun headersOf(name: String, values: List<String>): Headers = HeadersSingleImpl(name, values)

/**
 * Returns [Headers] instance from [pairs]
 */
public fun headersOf(vararg pairs: Pair<String, List<String>>): Headers = HeadersImpl(pairs.asList().toMap())

@Suppress("KDocMissingDocumentation")
public class HeadersImpl(
    values: Map<String, List<String>> = emptyMap()
) : Headers, StringValuesImpl(true, values) {
    override fun toString(): String = "Headers ${entries()}"
}

@Suppress("KDocMissingDocumentation")
public class HeadersSingleImpl(
    name: String,
    values: List<String>
) : Headers, StringValuesSingleImpl(true, name, values) {
    override fun toString(): String = "Headers ${entries()}"
}