import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock

fun main() {
    val sum = { x: Int, y: Int -> x + y }
    val action = { println(42) }

    val sum2: (Int, Int) -> Int = { x, y -> x + y }
    val action2: () -> Unit = { println(42) }

    println("abc".filter { it in 'b'..'z' })

    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString())
    println(letters.joinToString { it.lowercase(Locale.getDefault()) })
    println(
        letters.joinToString(separator = "! ", postfix = "! ",
            transform = { it.uppercase(Locale.getDefault()) })
    )

    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println("Shipping costs ${calculator(Order(3))}")

    println(averageWindowsDuration)

    println(log2.averageDurationFor(OS.WINDOWS))

    println(log2.averageDurationFor(OS.MAC))
}

inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

val averageWindowsDuration = log
    .filter { it.os == OS.WINDOWS }
    .map(SiteVisit::duration)
    .average()


data class SiteVisit2(
    val path: String,
    val duration: Double,
    val os: OS
)

val log2 = listOf(
    SiteVisit2("/", 34.0, OS.WINDOWS),
    SiteVisit2("/", 22.0, OS.MAC),
    SiteVisit2("/login", 12.0, OS.WINDOWS),
    SiteVisit2("/signup", 8.0, OS.IOS),
    SiteVisit2("/", 16.3, OS.ANDROID)
)

fun List<SiteVisit2>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit2::duration).average()

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }
    }

    return { order -> 1.2 * order.itemCount }
}

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: (T) -> String = { it.toString() } //함수 타입 파라미터 지정시 람다 디폴트 값 지정 toString
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))
    }

    result.append(postfix)
    return result.toString()
}

