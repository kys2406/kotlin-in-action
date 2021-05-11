fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxByOrNull { it.age })

    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))

    run(::salute)
}

fun salute() = println("Salute!!")

