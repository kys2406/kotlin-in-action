package me.kys2406

import me.kys2406.data.Person2
import me.kys2406.enum.Color
import me.kys2406.enum.Color2
import me.kys2406.smartcast.Expr
import me.kys2406.smartcast.Num
import me.kys2406.smartcast.Sum

fun main(args: Array<String>) {
    val person2 = Person2("ys", true)
    println(person2.name)
    println(person2.isMarried)

    //    person2.setMarried(false)
    person2.isMarried = false
    println(person2.isMarried)

    val rectangle = Rectangle(10, 11)
    println(rectangle.isSquare)

    val square = Rectangle(10, 10)
    println(square.isSquare)
}