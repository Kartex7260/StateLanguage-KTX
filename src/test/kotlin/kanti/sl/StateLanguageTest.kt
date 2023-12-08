package kanti.sl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StateLanguageTest {

	private val sl = StateLanguage {  }

	@Test
	fun serialize() {
		val expected = "User:fullClassName=STRING-kanti.sl.User,age=INTEGER-23,name=STRING-Alex"
		val actual = sl.serialize(
			User(
				name = "Alex",
				age = 23
			)
		)
		kotlin.test.assertEquals(expected, actual)
	}

	@Test
	fun deserialize() {
		val line = "User:fullClassName=STRING-kanti.sl.User,age=INTEGER-23,name=STRING-Alex"
		val expected = User(
			name = "Alex",
			age = 23
		)
		val actual = sl.deserialize<User>(line)
		assertEquals(
			expected,
			actual
		)
	}

}

data class User(
	val name: String,
	val age: Int
)