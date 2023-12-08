package kanti.sl.objects

import kanti.sl.SLContext
import kanti.sl.arguments.MutableStateArgument
import kanti.sl.arguments.asStateArgument
import kanti.sl.arguments.getOrThrow

fun MutableStateObject.getOrNull(key: String): MutableStateArgument? {
	return arguments.get(key)
}

fun MutableStateObject.getOrThrow(key: String): MutableStateArgument {
	return arguments.getOrThrow(key)
}

fun MutableStateObject.put(
	key: String,
	value: Any? = null
): MutableStateArgument {
	return arguments.put(key, value)
}

fun MutableStateObject.remove(key: String): MutableStateArgument? {
	return arguments.remove(key)
}

fun MutableStateObject.clearArgs() {
	return arguments.clear()
}

fun MutableStateObject(
	context: SLContext,
	name: String,
	vararg arg: MutableStateArgument
): MutableStateObject {
	return MutableStateObject.create(context, name, *arg)
}

fun MutableStateObject.toMutableStateObject(
	context: SLContext = this.context,
	name: String = this.name,
	vararg arg: MutableStateArgument = this.arguments.arguments.toTypedArray()
): MutableStateObject {
	if (
		this is MutableStateObjectImpl &&
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

fun MutableStateObject.copy(
	context: SLContext = this.context,
	name: String = this.name,
	vararg arg: MutableStateArgument = this.arguments.arguments.toTypedArray()
): MutableStateObject {
	return MutableStateObject(
		context = context,
		name = name,
		arg = arg
	)
}

val MutableStateObject.asStateObject: StateObject get() {
	return StateObject(
		context = context,
		name = name,
		arg = arguments.arguments.map { it.asStateArgument }.toTypedArray()
	)
}
