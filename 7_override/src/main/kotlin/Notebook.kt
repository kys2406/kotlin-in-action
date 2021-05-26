import kotlin.properties.Delegates

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

class User {
    var name: String by Delegates.observable("<no name>") {
            prop, old, new ->
        println("$old -> $new")
    }
}

fun main() {
    val n = 9
    println(0..(n + 1))
    (0..n).forEach {
        println(it) //0~9
    }

    println(lazyValue)
    println(lazyValue)

    val user = User()
    user.name = "first"
    user.name = "second"

    val p = Person()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")
    for ((attrName, value) in data)
        p.setAttribute(attrName, value)
    println(p.name)
}

private class Person {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }
    val name: String by _attributes
}