# 8장 고차 함수

## 고차함수 정의

> 정의 : 함수의 인자나, 반환값이 lambda 이거나 function reference 인 경우
>> list.filter { x > 0 }<br>

1. 함수 타입
    - 함수타입은 생략할 수도 있으나, 명시하려면 <b>(인자1:타입, 인자2:타입....) -> 반환타입</b> 으로 표기
    - 인자는 괄호로 묶여야 하고,
    - Unit은 반환값이 없음을 표시.. 실제 타입없이 사용할때는 없어도 되지만 타입을 명시할 때는 필요함.
    ```kotlin
    val sum = { x: Int, y: Int -> x + y }
    val action = { println(42) }
   
    val sum2: (Int, Int) -> Int = { x, y -> x + y } //파라미터 타입 + 반환 타입 -> 함수 타입 문법..
    val action2: () -> Unit = { println(42) }
    ```

2. 인자로 받은 함수 호출
   ```kotlin
    fun twoAndThree(operation: (Int, Int) -> Int) { //함수 타입인 파라미터 선언
        val result = operation(2, 3) //함수 타입인 파라미터 호출
        println("The result is $result")
    }

    fun main(args: Array) {
        twoAndThree { a, b -> a + b }
        twoAndThree { a, b -> a * b }
    }
    
    //filter -> filterTo
    inline fun <C : Appendable> CharSequence.filterTo(destination: C, predicate: (Char) -> Boolean): C {
        for (index in 0 until length) {
            val element = get(index)
            if (predicate(element)) destination.append(element)
        }
        return destination
    }

   ```
   ![img.png](img.png)

3. 자바에서 코틀린 함수 타입 사용
    ```java
    //자바8 이상 람다식
    processTheAnswer(x -> x + 1)

    //자바8 이전 Function
    processTheAnswer(new Function1<Integer, Interger> {
        @Override
        public Integer invoke(Integer number) {
            System.out.println(number)
            return number +1
        }
    }
   
   //자바
    List lists = new ArrayList<>();
    list.add("abc");
    list.add("def");

    CollectionKt.forEach(lists, str -> {
        System.out.prinlnt(str);
        return Unit.INSTANCE; //Unit은 반드시 반환
    }
    ```

4. 디폴트 값을 지정한 함수 타입 파라미터나 널이 될 수 있는 함수 타입 파라미터
    ```kotlin
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
   
    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString())
    println(letters.joinToString { it.lowercase(Locale.getDefault()) })
    println(
        letters.joinToString(separator = "! ", postfix = "! ",
            transform = { it.uppercase(Locale.getDefault()) })
    )
    ```

5. 함수를 함수에서 반환
    ```kotlin
    enum class Delivery { STANDARD, EXPEDITED }

    class Order(val itemCount: Int)

    fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double { //Return 값이 함수..
        if (delivery == Delivery.EXPEDITED) {
            return { order -> 6 + 2.1 * order.itemCount }
        }
        return { order -> 1.2 * order.itemCount }
    }
    ```

6. 중복제거
    ```kotlin
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
    ```

## 인라인 함수

> 람다 -> 익명 클래스 -> 매번 생성되지는 않지만 1회는 생성해야함 -> 성능저하<br>
> 해결방법?? -> inline function

```kotlin
//예제
inline fun String.filter(predicate: (Char) -> Boolean): String {
    return filterTo(StringBuilder(), predicate).toString()
}
```

1. 동작방식
    ```kotlin
    inline fun <T> synchronized(lock: Lock, action: () -> T): T {
        lock.lock()
        try {
            return action()
        } finally {
            lock.unlock()
        }
    }
    ```

2. 

## 결론

```markdown
자유로운 연산자 오버로딩 및 다양한 컨벤션의 확장 -> 자율성 보장함!!
```