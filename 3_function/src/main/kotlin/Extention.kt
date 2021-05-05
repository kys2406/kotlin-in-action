package strings

import java.lang.StringBuilder

fun String.lastChar() : Char = get(length - 1)

//fun StringBuilder.lastChar: Char
//    get() = get(length - 1)
//    set(value: Char) {
//        this.setCharAt(length-1, value)
//    }
