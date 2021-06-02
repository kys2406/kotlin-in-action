import kotlin.random.Random

fun main() {
//    printContents(listOf("abc", "def"))
//    val list = addAnswer(listOf("abc", "def").toMutableList())
//    println(list.minByOrNull { it::class.java.name })

//    val list: MutableList<Any?> = mutableListOf('a', 1, "hello")
//    val chars = mutableListOf('a', 'b', 'c')
//    val unknownElements: MutableList<*> = if (Random.nextBoolean()) list else chars
//    unknownElements.add(42)
//    println(unknownElements.First())

    printSum(listOf(1, 2, 3))
//    val list = listOf(1,2,3)
//    if (list is List<Int>) {
//        println("ok")
//    }
    println(isA<String>("abc"))
    println(isA<Int>(123))

    val list = listOf("test", 1)
    println(list.filterIsInstance<Int>())
}

inline fun <reified T> isA(value: Any) = value is T

fun printSum(c: Collection<*>) {
    val intList = c as? List<Int>  // 여기서 warning 발생
        ?: throw IllegalArgumentException("List<Int> is expected")
    println(intList.sum())
}


fun test(i: Int) {
    val n: Number = i
    fun f(s: String) {

    }
//    f(i) // 컴파일 에러
}

//fun test(i: Number) {
//    val n: Int = i
//    fun f(s: String) {
//
//    }
//    f(i) // 컴파일 에러
//}

interface Producer<out T> { //클래스가 T에 대해서 공변적이라고 선언
    fun produce(): T
}

fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>): List<Any> {
    list.add(42)
    return list
}

open class Animal {
    fun feed() {
        println("먹었다!!")
    }
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Cat : Animal() {
    fun cleanLitter() {

    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
    feedAll(cats)
}

//무리...
class Herd<out T : Animal> {
    val size: Int
        get() {
            TODO()
        }

    operator fun get(i: Int): T {
        TODO()
    }
}

