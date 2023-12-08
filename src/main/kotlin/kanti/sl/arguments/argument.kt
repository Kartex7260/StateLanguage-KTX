package kanti.sl.arguments

import kanti.sl.SLContext

inline fun <reified T> StateArgument.getAsCustom(): T? {
	val v = value
	if (v !is T)
		return null
	return v
}

val StateArgument.asBoolean: Boolean? get() {
	val v = value
	if (v !is Boolean)
		return null
	return v
}

val StateArgument.asByte: Byte? get() {
	val v = value
	if (v !is Byte)
		return null
	return v
}

val StateArgument.asShort: Short? get() {
	val v = value
	if (v !is Short)
		return null
	return v
}

val StateArgument.asInt: Int? get() {
	val v = value
	if (v !is Int)
		return null
	return v
}

val StateArgument.asLong: Long? get() {
	val v = value
	if (v !is Long)
		return null
	return v
}

val StateArgument.asFloat: Float? get() {
	val v = value
	if (v !is Float)
		return null
	return v
}

val StateArgument.asDouble: Double? get() {
	val v = value
	if (v !is Double)
		return null
	return v
}

val StateArgument.asString: String? get() {
	val v = value
	if (v !is String)
		return null
	return v
}

inline fun <reified T> StateArgument.requireCustom(): T {
	return value as T
}

fun StateArgument.requireBoolean(): Boolean {
	return value as Boolean
}

fun StateArgument.requireByte(): Byte {
	return value as Byte
}

fun StateArgument.requireShort(): Short {
	return value as Short
}

fun StateArgument.requireInt(): Int {
	return value as Int
}

fun StateArgument.requireLong(): Long {
	return value as Long
}

fun StateArgument.requireFloat(): Float {
	return value as Float
}

fun StateArgument.requireDouble(): Double {
	return value as Double
}

fun StateArgument.requireString(): String {
	return value as String
}

fun StateArgument(
	context: SLContext,
	key: String,
	value: Any
): StateArgument {
	return StateArgument.create(context, key, value)
}

fun StateArgument.copy(
	context: SLContext = this.context,
	key: String = this.key,
	value: Any = this.value
): StateArgument {
	return StateArgument(
		context = context,
		key = key,
		value = value
	)
}

fun StateArgument.toStateArgument(
	context: SLContext = this.context,
	key: String = this.key,
	value: Any = this.value
): StateArgument {
	if (
		this is StateArgumentImpl &&
		context == this.context &&
		key == this.key &&
		value == this.value
	) {
		return this
	}
	return copy(
		context = context,
		key = key,
		value = value
	)
}

val StateArgument.asMutable: MutableStateArgument get() {
	return MutableStateArgument(
		context = context,
		key = key,
		value = value
	)
}
