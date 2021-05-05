import strings.lastChar as last

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)

//    println(joinToString(list, "; ", "(", ")"))

    //파라미터 이름을 붙임..
//    println(joinToString(list, separator = ", ", prefix = "(", postfix = ")"))

    //Defulat 파라미터 지정 후 값 생량
//    println(joinToString(list))

//    performOperation();
//    reportOperationCount();
//    println(UNIX_LINE_SEPERATOR)

//    println("Kotlin".lastChar())
//    println("Kotlin".last())

//    println(list.joinToString(","))
//    println("Kotlin".last)

//    val map = hashMapOf(1 to "one", 7.to("seven"), 53 to "fifty-three")

    val kotlinLogo =
        """| //
           .| // 
           .|/ \""".trimMargin(".")

    println(kotlinLogo)

}

//Generic한 함수....
@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ", //Default 값 지정
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}