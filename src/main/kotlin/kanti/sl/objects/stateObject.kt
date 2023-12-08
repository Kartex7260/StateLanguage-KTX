package kanti.sl.objects

import kanti.sl.SLContext
import kanti.sl.arguments.*

fun StateObject.getOrNull(key: String): StateArgument? {
	return arguments.get(key)
}

fun StateObject.getOrThrow(key: String): StateArgument {
	return arguments.getOrThrow(key)
}

fun StateObject.getValue(key: String): Any {
	return arguments.getValue(key)
}

fun StateObject.getBoolean(key: String): Boolean {
	return arguments.getBoolean(key)
}

fun StateObject.getByte(key: String): Byte {
	return arguments.getByte(key)
}

fun StateObject.getShort(key: String): Short {
	return arguments.getShort(key)
}

fun StateObject.getInt(key: String): Int {
	return arguments.getInt(key)
}

fun StateObject.getLong(key: String): Long {
	return arguments.getLong(key)
}

fun StateObject.getFloat(key: String): Float {
	return arguments.getFloat(key)
}

fun StateObject.getDouble(key: String): Double {
	return arguments.getDouble(key)
}

fun StateObject.getString(key: String): String {
	return arguments.getString(key)
}

inline fun <reified T> StateObject.getCustom(key: String): T {
	return arguments.getCustom(key)
}

fun StateObject.getValueOrNull(key: String): Any? {
	return arguments.getValueOrNull(key)
}

fun StateObject.getBooleanOrNull(key: String): Boolean? {
	return arguments.getBooleanOrNull(key)
}

fun StateObject.getByteOrNull(key: String): Byte? {
	return arguments.getByteOrNull(key)
}

fun StateObject.getShortOrNull(key: String): Short? {
	return arguments.getShortOrNull(key)
}

fun StateObject.getIntOrNull(key: String): Int? {
	return arguments.getIntOrNull(key)
}

fun StateObject.getLongOrNull(key: String): Long? {
	return arguments.getLongOrNull(key)
}

fun StateObject.getFloatOrNull(key: String): Float? {
	return arguments.getFloatOrNull(key)
}

fun StateObject.getDoubleOrNull(key: String): Double? {
	return arguments.getDoubleOrNull(key)
}

fun StateObject.getStringOrNull(key: String): String? {
	return arguments.getStringOrNull(key)
}

inline fun <reified T> StateObject.getCustomOrNull(key: String): T? {
	return arguments.getCustomOrNull(key)
}

fun StateObject.getValueOrDefault(key: String, default: Any): Any {
	return arguments.getValueOrDefault(key, default)
}

fun StateObject.getBooleanOrDefault(key: String, default: Boolean = false): Boolean {
	return arguments.getBooleanOrDefault(key, default)
}

fun StateObject.getByteOrDefault(key: String, default: Byte = 0): Byte {
	return arguments.getByteOrDefault(key)
}

fun StateObject.getShortOrDefault(key: String, default: Short = 0): Short {
	return arguments.getShortOrDefault(key)
}

fun StateObject.getIntOrDefault(key: String, default: Int = 0): Int {
	return arguments.getIntOrDefault(key, default)
}

fun StateObject.getLongOrDefault(key: String, default: Long = 0): Long {
	return arguments.getLongOrDefault(key, default)
}

fun StateObject.getFloatOrDefault(key: String, default: Float = 0.0f): Float {
	return arguments.getFloatOrDefault(key)
}

fun StateObject.getDoubleOrDefault(key: String, default: Double = 0.0): Double {
	return arguments.getDoubleOrDefault(key)
}

fun StateObject.getStringOrDefault(key: String, default: String = ""): String {
	return arguments.getStringOrDefault(key, default)
}

inline fun <reified T> StateObject.getCustomOrDefault(key: String, default: T): T {
	return arguments.getCustomOrDefault(key, default)
}

fun StateObject(
	context: SLContext,
	name: String,
	vararg arg: StateArgument
): StateObject {
	return StateObject.create(context, name, *arg)
}

fun StateObject.toStateObject(
	context: SLContext = this.context,
	name: String = this.name,
	vararg arg: StateArgument = this.arguments.arguments.toTypedArray()
): StateObject {
	if (
		this is StateObjectImpl &&
		context == this.context &&
		name == this.name &&
		arg.contentEquals(this.arguments.arguments.toTypedArray())
	) {
		return this
	}
	return copy(
		context = context,
		name = name,
		arg = arg
	)
}

fun StateObject.copy(
	context: SLContext = this.context,
	name: String = this.name,
	vararg arg: StateArgument = this.arguments.arguments.toTypedArray()
): StateObject {
	return StateObject(
		context = context,
		name = name,
		arg = arg
	)
}

val StateObject.asMutable: MutableStateObject get() {
	return MutableStateObject(
		context = context,
		name = name,
		arg = arguments.arguments.map { it.asMutable }.toTypedArray()
	)
}
