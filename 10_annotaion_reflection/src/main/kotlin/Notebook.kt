import kotlin.reflect.KFunction2
import kotlin.reflect.full.memberProperties

fun main() {
    val person = Person("Alice", 27)
    val kClass = person.javaClass.kotlin
    println(kClass.simpleName)

    kClass.memberProperties.forEach { println(it.name) }

    val kFunction = ::foo
    kFunction.call(42)

    val kFunction2: KFunction2<Int, Int, Int> = ::sum
    println(kFunction2.invoke(1, 2) + kFunction2.invoke(3, 4))

    val kProperty = ::counter
    kProperty.setter.call(21)
    println(kProperty.get())
}

var counter = 0

fun foo(x: Int) = println(x)

fun sum(x: Int, y: Int) = x + y

class Person(val name: String, val age: Number)