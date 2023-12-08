package kanti.sl

import kanti.sl.arguments.values.SupportedValues

fun SLContext.Builder.setSupportedValues(
	building: SupportedValues.Builder.() -> Unit
): SLContext.Builder {
	val builder = SupportedValues.builder()
	builder.building()
	setSupportedValues(builder)
	return this
}

fun SLContext.Builder.setObjectSerializer(
	building: StateObjectSerializer.Builder.() -> Unit
): SLContext.Builder {
	val builder = StateObjectSerializer.builder()
	builder.building()
	setObjectSerializer(builder)
	return this
}
