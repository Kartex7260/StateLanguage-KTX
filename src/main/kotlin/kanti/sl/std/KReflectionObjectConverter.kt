package kanti.sl.std

import kanti.sl.arguments.MutableStateArguments
import kanti.sl.arguments.StateArguments
import kanti.sl.arguments.requireString
import kanti.sl.isSupported
import kanti.sl.std.annotations.Argument
import kanti.sl.std.annotations.Ignore
import kanti.sl.std.annotations.Select
import java.util.Comparator
import kotlin.reflect.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmErasure

class KReflectionObjectConverter : BaseStateObjectConverter() {

	private val fullClassNameKey = "fullClassName"
	private val methodGetPrefix = "get"

	override fun convert(args: MutableStateArguments, obj: Any) {
		val type = obj::class
		args.put(fullClassNameKey, type.qualifiedName)
		val functions = getGetMethods(type)

		for (func in functions) {
			val key = getArgumentKey(func)
			val value = func.call(obj)
				?: throw IllegalStateException("Unexpected error when getting the value")
			args.put(key, value)
		}
	}

	override fun convert(args: StateArguments): Any {
		val className = getClassName(args)
		val type = Class.forName(className).kotlin
		val constructor = getConstructor(type)
		val values = mutableListOf<Any>()
		for (parameter in constructor.parameters) {
			val key = getArgumentKey(parameter)
			val argument = args[key] ?:
			throw IllegalArgumentException("Not found parameter $key")
			values.add(argument.value)
		}
		return constructor.call(*values.toTypedArray()) ?:
		throw RuntimeException("Unexpected error when creating an instance")
	}

	private fun getGetMethods(type: KClass<*>): List<KCallable<*>> {
		val methods = mutableListOf<KCallable<*>>()
		for (method in type.members) {
			if (method !is KProperty<*>)
				continue
			if (method.visibility != KVisibility.PUBLIC)
				continue
			if (method.parameters.size > 1)
				continue
			if (!isSupported(method.returnType.jvmErasure.java))
				continue
			if (method.returnType.isMarkedNullable)
				continue
			val ignore = method.findAnnotation<Ignore>()
			if (ignore != null)
				continue
			methods.add(method)
		}
		return methods
	}

	private fun getArgumentKey(func: KCallable<*>): String {
		val argument = func.findAnnotation<Argument>()
		if (argument != null) {
			return argument.name
		}
		return func.name
	}

	private fun getArgumentKey(parameter: KParameter): String {
		return parameter.findAnnotation<Argument>()?.name ?:
		parameter.name ?:
		throw IllegalStateException("Parameter not have ${Argument::class.qualifiedName} annotation");
	}

	private fun getClassName(args: StateArguments): String {
		val classNameArgument = args[fullClassNameKey]
			?:throw IllegalArgumentException("Not found full class name")
		return classNameArgument.requireString()
	}

	private fun getConstructor(type: KClass<*>): KFunction<*> {
		return type.constructors.firstOrNull {
			it.findAnnotation<Select>() != null
		} ?: type.constructors.maxWithOrNull(
			Comparator.comparingInt { it.parameters.size }
		) ?: throw IllegalStateException("Not found relevant constructor")
	}
}