package kuest

public fun <Value : Any> caseInsensitiveMap(): MutableMap<String, Value> = CaseInsensitiveMap()

// TODO: Maybe consider actually implementing an unmodifiable list
public fun <T> Set<T>.unmodifiable(): Set<T> = this