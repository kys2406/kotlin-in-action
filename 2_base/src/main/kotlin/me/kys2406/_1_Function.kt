package me.kys2406

fun main(args: Array<String>) {
    println("Hello, World")

    println(max(1, 2))
    println(max2(10, 2))

    val name = "코틀린"
    println("Hello, $name")
    println("Hello, ${name}")
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int) = if (a > b) a else b