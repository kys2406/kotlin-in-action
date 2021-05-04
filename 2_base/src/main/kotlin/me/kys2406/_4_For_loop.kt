package me.kys2406

fun main(args: Array<String>) {
//    val ranges = 1..100 //1~10까지.. 10이 포함된다.. 자바랑 다르다..
//    for (i in ranges) {
//        println(_3_6_9(i))
//    }
//
//    for (i in 100 downTo 1 step 2) {
//        println(_3_6_9(i))
//    }
//
//    for (i in 0 until 100) {
//        println(_3_6_9(i))
//    }
//
//    for (i in 0..99) {
//        println(_3_6_9(i))
//    }

//    val binaryReps = TreeMap<Char, String>()
//    for (c in 'A'..'F') {
//        val binary = Integer.toBinaryString(c.toInt())
//        binaryReps[c] = binary
//    }
//
//    for ((letter, binary) in binaryReps) {
//        println("$letter = $binary")
//    }
//
//    val list = arrayListOf("10", "11", "1001")
//    for ((index, element) in list.withIndex()) {
//        println("$index = $element")
//    }

    println(isLetter('X'))
    println(isLetter('1'))
    println(isNotDigit('X'))
    println(isNotDigit('1'))

    println(recognize('X'))
    println(recognize('1'))
    println(recognize('!'))

    // 문자열 비교... J < K < S...
    println("Kotlin" in "Java".."Scala")

    // List("Java" , "Scala")
    println("Kotlin" in setOf("Java", "Scala"))

}

fun _3_6_9(i: Int) = when {
    i % 3 == 0 -> "짝"
    i % 10 == 0 -> "뿌쑝"
    else -> "$i"
}

//in, not in
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "DIGIT"
    in 'a'..'z', in 'A'..'Z' -> "LETTER"
    else -> "NO!!!!!"
}