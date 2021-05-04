package me.kys2406

import me.kys2406.data.Person2
import me.kys2406.enum.Color
import me.kys2406.enum.Color2
import me.kys2406.smartcast.Expr
import me.kys2406.smartcast.Num
import me.kys2406.smartcast.Sum

fun main(args: Array<String>) {
    println(Color2.RED.rgb())

    println(getMnemonitc(Color.BLUE))
    println(getMnemonitc(Color.VIOLET))

    println(mix(Color.YELLOW, Color.BLUE))
    println(mix(Color.YELLOW, Color.RED))
    println(mix2(Color.RED, Color.YELLOW))

    //(1 + 2) + 4
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun getMnemonitc(color: Color) = when (color) {
    Color.BLUE -> "Battle"
    Color.RED -> "Ri"
    Color.GREEN, Color.INDIGO -> "Of"
    else -> "111"
}

fun mix(color1: Color, color2: Color) = when (setOf(color1, color2)) {
    setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
    setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
    setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
    else -> throw Exception("DIRTY")
}

// 인자 없는 when 예제 인데... 더불편해 보임...
fun mix2(color1: Color, color2: Color) = when {
    (color1 == Color.RED && color2 == Color.YELLOW) ||
            (color2 == Color.RED && color1 == Color.YELLOW) -> Color.ORANGE

    else -> throw Exception("DIRTY")
}

fun eval(e: Expr): Int =
        when (e) {
            //instanceOf와 유사
            is Num -> {
                println("num : ${e.value}")
                e.value
            }
            is Sum -> eval(e.right) + eval(e.left) // 스마트캐스트..
            else -> throw IllegalArgumentException("Unknown Expression")
        }