package kanti.sl.arguments

import kanti.sl.SLContext

fun MutableStateArgument(
	context: SLContext,
	key: String,
	value: Any? = null
): MutableStateArgument {
	return MutableStateArgument.create(context, key, value)
}

fun MutableStateArgument.copy(
	context: SLContext = this.context,
	key: String = this.key,
	value: Any = this.value
): MutableStateArgument {
	return MutableStateArgument(
		context = context,
		key = key,
		value = value
	)
}

fun MutableStateArgument.toMutableStateArgument(
	context: SLContext = this.context,
	key: String = this.key,
	value: Any = this.value
): MutableStateArgument {
	if (
		this is MutableStateArgumentImpl &&
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

val MutableStateArgument.asStateArgument: StateArgument get() {
	return StateArgument(
		context = context,
		key = key,
		value = value
	)
}
