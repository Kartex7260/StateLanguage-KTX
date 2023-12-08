package kanti.sl.arguments

import kanti.sl.SLContext

fun MutableStateArguments.getOrThrow(key: String): MutableStateArgument {
	return get(key) ?: throw IllegalArgumentException("Not found argument by key=$key")
}

fun MutableStateArguments(
	context: SLContext,
	vararg arg: MutableStateArgument
): MutableStateArguments {
	return MutableStateArguments.create(context, *arg)
}

fun MutableStateArguments.copy(
	context: SLContext = this.context,
	vararg arg: MutableStateArgument = this.arguments.map { it.copy() }.toTypedArray()
): MutableStateArguments {
	return MutableStateArguments(
		context = context,
		arg = arg
	)
}

val MutableStateArguments.asStateArguments: StateArguments get() {
	return StateArguments(
		context = context,
		arg = arguments.map { it.asStateArgument }.toTypedArray()
	)
}
