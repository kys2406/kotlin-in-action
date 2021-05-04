package me.kys2406.enum

enum class Color2(
        val r: Int,
        val g: Int,
        val b: Int
) {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255);

    fun rgb() = (r * 255 + g) * 256 + b
}