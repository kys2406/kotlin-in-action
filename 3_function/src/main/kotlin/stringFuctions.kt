@file:JvmName("StringFunctions")

package strings

@JvmOverloads
fun <T> Collection<T>.joinToString(
    separator: String = ", ", //Default 값 지정
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) { // 수신객체..
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

val String.lastChar: Char
    get() = get(length - 1)

fun parsePath(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("directory : $directory, filename : $filename, extension : $extension")
    }
}