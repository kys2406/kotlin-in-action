//패키지 구조와 꼭 같지 않아도 됨
package me.kys2406

class Rectangle(
        val height: Int,
        val width: Int
) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}