var opCount = 0
const val UNIX_LINE_SEPERATOR = "\n"

fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("Operation performed $opCount times")
}