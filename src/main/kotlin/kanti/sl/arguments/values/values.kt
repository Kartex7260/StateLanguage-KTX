package kanti.sl.arguments.values

import kanti.sl.SLContext

fun SupportedValues(
	context: SLContext? = null,
	building: SupportedValues.Builder.() -> Unit
): SupportedValues {
	val builder = SupportedValues.builder()
	builder.building()
	context?.let {
		builder.setContext(it)
	}
	return builder.build()
}

fun SupportedValue(
	type: Class<*>,
	context: SLContext? = null,
	building: SupportedValue.Builder.() -> Unit
): SupportedValue {
	val builder = SupportedValue.builder()
	builder.building()
	context?.let {
		builder.setContext(it)
	}
	return builder.build()
}

fun ValueCheckable(
	check: (value: Class<*>) -> Boolean
): ValueCheckable {
	return object : BaseValueCheckable() {

		override fun check(value: Any): Boolean {
			return check(value.javaClass)
		}

		override fun check(type: Class<*>): Boolean {
			return check(type)
		}
	}
}

fun ValueSerializer(
	serialize: (value: Any) -> String,
	deserialize: (line: String) -> Any
): ValueSerializer {
	return object : BaseValueSerializer() {

		override fun serialize(value: Any): String {
			return serialize(value)
		}

		override fun deserialize(line: String): Any {
			return deserialize(line)
		}
	}
}

fun ValueNormalizer(
	normalize: (value: Any) -> Any
): ValueNormalizer {
	return object : BaseValueNormalizer() {
		override fun normalize(value: Any): Any {
			return normalize(value)
		}
	}
}

fun SupportedValue.Builder.setCheckable(
	check: (type: Class<*>) -> Boolean
): SupportedValue.Builder {
	setCheckable(ValueCheckable(check))
	return this
}

fun SupportedValue.Builder.setSerializer(
	serialize: (value: Any) -> String,
	deserialize: (line: String) -> Any
): SupportedValue.Builder {
	val serializer = ValueSerializer(
		serialize = serialize,
		deserialize = deserialize
	)
	setSerializer(serializer)
	return this
}

fun SupportedValues.Builder.setDefaultSupportedValue(
	building: SupportedValue.Builder.() -> Unit
): SupportedValues.Builder {
	val builder = SupportedValue.builder(Class::class.java)
	builder.building()
	setDefaultSupportedValue(builder)
	return this
}

inline fun <reified T> SupportedValues.Builder.registerSupportedValue(
	building: SupportedValue.Builder.() -> Unit
): SupportedValues.Builder {
	val type = T::class.java
	val builder = SupportedValue.builder(type)
	builder.setPrefix(type.simpleName.uppercase())
	builder.building()
	registerSupportedValue(builder)
	return this
}

inline fun <reified T> SupportedValues.Builder.registerValueNormalizer(
	noinline normalize: (value: Any) -> Any
): SupportedValues.Builder {
	registerValueNormalizer(T::class.java, ValueNormalizer(normalize))
	return this
}
