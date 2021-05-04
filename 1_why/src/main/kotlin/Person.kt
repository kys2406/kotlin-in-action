data class Person(val name: String,
                  val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(
            Person("영희"),
            Person("철수", age = 29),
            Person("YS", 33),
    )

    val oldest = persons.maxByOrNull { it.age ?: 0 } //엘비스 연산자(Optional의 orElse 와 유사..// )
    print("나이 많은 사람 : $oldest")
}
