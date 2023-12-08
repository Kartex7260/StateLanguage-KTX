package kanti.sl.arguments

import kanti.sl.SLContext

fun StateArguments.getOrThrow(key: String): StateArgument {
	return get(key) ?: throw IllegalArgumentException("Not found argument by key=$key")
}

fun StateArguments.getValue(key: String): Any {
	return getOrThrow(key)
}

fun StateArguments.getBoolean(key: String): Boolean {
	return getOrThrow(key).requireBoolean()
}

fun StateArguments.getByte(key: String): Byte {
	return getOrThrow(key).requireByte()
}

fun StateArguments.getShort(key: String): Short {
	return getOrThrow(key).requireShort()
}

fun StateArguments.getInt(key: String): Int {
	return getOrThrow(key).requireInt()
}

fun StateArguments.getLong(key: String): Long {
	return getOrThrow(key).requireLong()
}

fun StateArguments.getFloat(key: String): Float {
	return getOrThrow(key).requireFloat()
}

fun StateArguments.getDouble(key: String): Double {
	return getOrThrow(key).requireDouble()
}

fun StateArguments.getString(key: String): String {
	return getOrThrow(key).requireString()
}

inline fun <reified T> StateArguments.getCustom(key: String): T {
	return getOrThrow(key).requireCustom()
}

fun StateArguments.getValueOrNull(key: String): Any? {
	return get(key)?.value
}

fun StateArguments.getBooleanOrNull(key: String): Boolean? {
	return get(key)?.requireBoolean()
}

fun StateArguments.getByteOrNull(key: String): Byte? {
	return get(key)?.requireByte()
}

fun StateArguments.getShortOrNull(key: String): Short? {
	return get(key)?.requireShort()
}

fun StateArguments.getIntOrNull(key: String): Int? {
	return get(key)?.requireInt()
}

fun StateArguments.getLongOrNull(key: String): Long? {
	return get(key)?.requireLong()
}

fun StateArguments.getFloatOrNull(key: String): Float? {
	return get(key)?.requireFloat()
}

fun StateArguments.getDoubleOrNull(key: String): Double? {
	return get(key)?.requireDouble()
}

fun StateArguments.getStringOrNull(key: String): String? {
	return get(key)?.requireString()
}

inline fun <reified T> StateArguments.getCustomOrNull(key: String): T? {
	return get(key)?.requireCustom()
}

fun StateArguments.getValueOrDefault(key: String, default: Any): Any {
	return getValueOrNull(key) ?: default
}

fun StateArguments.getBooleanOrDefault(key: String, default: Boolean = false): Boolean {
	return getBooleanOrNull(key) ?: default
}

fun StateArguments.getByteOrDefault(key: String, default: Byte = 0): Byte {
	return getByteOrNull(key) ?: default
}

fun StateArguments.getShortOrDefault(key: String, default: Short = 0): Short {
	return getShortOrNull(key) ?: default
}

fun StateArguments.getIntOrDefault(key: String, default: Int = 0): Int {
	return getIntOrNull(key) ?: default
}

fun StateArguments.getLongOrDefault(key: String, default: Long = 0): Long {
	return getLongOrNull(key) ?: default
}

fun StateArguments.getFloatOrDefault(key: String, default: Float = 0.0f): Float {
	return getFloatOrNull(key) ?: default
}

fun StateArguments.getDoubleOrDefault(key: String, default: Double = 0.0): Double {
	return getDoubleOrNull(key) ?: default
}

fun StateArguments.getStringOrDefault(key: String, default: String = ""): String {
	return getStringOrNull(key) ?: default
}

inline fun <reified T> StateArguments.getCustomOrDefault(key: String, default: T): T {
	return getCustomOrNull(key) ?: default
}

fun StateArguments(
	context: SLContext,
	vararg arg: StateArgument
): StateArguments {
	return StateArguments.create(context, *arg)
}

fun StateArguments.copy(
	context: SLContext = this.context,
	vararg arg: StateArgument = this.arguments.map { it.copy() }.toTypedArray()
): StateArguments {
	return StateArguments(
		context = context,
		arg = arg
	)
}

val StateArguments.asMutable: MutableStateArguments get() {
	return MutableStateArguments(
		context = context,
		arg = arguments.map { it.asMutable }.toTypedArray()
	)
}
