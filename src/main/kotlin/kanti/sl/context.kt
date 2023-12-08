package kanti.sl

import kanti.sl.arguments.values.SupportedValue
import kanti.sl.objects.StateObject

val SLContext.prefixValueSeparator: String get() {
	return supportedValues.prefixValueSeparator
}

fun SLContext.getType(type: Class<*>): SupportedValue? {
	return supportedValues.getType(type)
}

fun SLContext.requireType(type: Class<*>): SupportedValue {
	return getType(type) ?: throw IllegalArgumentException("Not found supported type ${type.name}")
}

fun SLContext.normalize(to: Class<*>, value: Any): Any {
	return supportedValues.normalize(to, value)
}

fun SLContext.normalize(value: Any): Any {
	return supportedValues.normalize(value)
}

inline fun <reified T> SLContext.typeNormalize(value: Any): T {
	return normalize(value) as T
}

inline fun <reified T> SLContext.normalizeTo(value: Any): T {
	return normalize(T::class.java, value) as T
}

fun SLContext.check(value: Any): Boolean {
	return supportedValues.check(value)
}

fun SLContext.isSupported(type: Class<*>): Boolean {
	return supportedValues.isSupported(type)
}

fun SLContext.serializeValue(value: Any): String {
	return supportedValues.serialize(value)
}

fun SLContext.deserializeValue(line: String): Any {
	return supportedValues.deserialize(line)
}

inline fun <reified T> SLContext.typeDeserializeValue(line: String): T {
	return supportedValues.deserialize(line) as T
}

fun SLContext.determineValueType(line: String): SupportedValue {
	return supportedValues.determineValueType(line)
}

fun SLContext.getCleanPrefix(line: String): String {
	return supportedValues.getCleanPrefix(line)
}

fun SLContext.getCleanLine(line: String): String {
	return supportedValues.getCleanLine(line)
}

fun SLContext.serialize(stateObject: StateObject): String {
	return stateObjectSerializer.serialize(stateObject)
}

fun SLContext.deserialize(line: String): StateObject {
	return stateObjectSerializer.deserialize(line)
}

val SLContextOwner.prefixValueSeparator: String get() {
	return context.prefixValueSeparator
}

fun SLContextOwner.getType(type: Class<*>): SupportedValue? {
	return context.getType(type)
}

fun SLContextOwner.requireType(type: Class<*>): SupportedValue {
	return context.requireType(type)
}

fun SLContextOwner.normalize(to: Class<*>, value: Any): Any {
	return context.normalize(to, value)
}

fun SLContextOwner.normalize(value: Any): Any {
	return context.normalize(value)
}

inline fun <reified T> SLContextOwner.typeNormalize(value: Any): T {
	return context.typeNormalize<T>(value)
}

inline fun <reified T> SLContextOwner.normalizeTo(value: Any): T {
	return context.normalizeTo(value)
}

fun SLContextOwner.check(value: Any): Boolean {
	return context.check(value)
}

fun SLContextOwner.isSupported(type: Class<*>): Boolean {
	return context.isSupported(type)
}

fun SLContextOwner.serializeValue(value: Any): String {
	return context.serializeValue(value)
}

fun SLContextOwner.deserializeValue(line: String): Any {
	return context.deserializeValue(line)
}

inline fun <reified T> SLContextOwner.typeDeserializeValue(line: String): T {
	return context.typeDeserializeValue<T>(line)
}

fun SLContextOwner.determineValueType(line: String): SupportedValue {
	return context.determineValueType(line)
}

fun SLContextOwner.getCleanPrefix(line: String): String {
	return context.getCleanPrefix(line)
}

fun SLContextOwner.getCleanLine(line: String): String {
	return context.getCleanLine(line)
}

fun SLContextOwner.serialize(stateObject: StateObject): String {
	return context.serialize(stateObject)
}

fun SLContextOwner.deserialize(line: String): StateObject {
	return context.deserialize(line)
}
