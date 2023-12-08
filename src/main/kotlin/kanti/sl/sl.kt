package kanti.sl

import kanti.sl.arguments.MutableStateArguments
import kanti.sl.arguments.StateArguments
import kanti.sl.std.BaseStateObjectConverter
import kanti.sl.std.KReflectionObjectConverter


fun test() {
	val sl = StateLanguage {  }
}

fun StateLanguage(
	block: StateLanguage.Builder.() -> Unit
): StateLanguage {
	val builder = StateLanguage.builder()
		.setDefaultObjectConverter(KReflectionObjectConverter())
	builder.block()
	return builder.build()
}

inline fun <reified T> StateLanguage.deserialize(line: String): T {
	return deserialize(T::class.java, line) as T
}

inline fun <reified T> StateLanguage.serialize(obj: T): String {
	return serialize(T::class.java, obj as Any)
}

fun StateLanguage.Builder.setContext(
	building: SLContext.Builder.() -> Unit
): StateLanguage.Builder {
	val builder = SLContext.builder()
	builder.building()
	setContext(builder)
	return this
}

fun StateLanguage.Builder.setDefaultObjectConverter(
	convertFrom: (args: MutableStateArguments, obj: Any) -> Unit,
	convertTo: (args: StateArguments) -> Any
): StateLanguage.Builder {
	val defConverter = object : BaseStateObjectConverter() {

		override fun convert(args: MutableStateArguments, obj: Any) {
			convertFrom(args, obj)
		}

		override fun convert(args: StateArguments): Any {
			return convertTo(args)
		}
	}
	setDefaultObjectConverter(defConverter)
	return this
}

inline fun <reified T> StateLanguage.Builder.registerConverter(
	crossinline convertFrom: (args: MutableStateArguments, obj: T) -> Unit,
	crossinline convertTo: (args: StateArguments) -> T
): StateLanguage.Builder {
	val converter = object : BaseStateObjectConverter() {

		override fun convert(args: MutableStateArguments, obj: Any) {
			convertFrom(args, obj as T)
		}

		override fun convert(args: StateArguments): Any {
			return convertTo(args) as Any
		}
	}
	registerConverter(T::class.java, converter)
	return this
}
