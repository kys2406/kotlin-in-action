# 8장 제네릭스

## Type Parameeter

> 자바와 다르지 않음 + 알파

1. 함수와 프로퍼티

```kotlin
fun <T> List<T>.slice(indices: IntRange): List<T>
```

2. 클래스
    - 자바와 동일

3. Limitation
    - 자바 : extends나 super를 사용하여 사용한 타입을 제한
        - <T extends Number> T sum(List<T> list)
    - 코틀린 : ":"을 이용한 upper bound 설정
        - fun <T: Number> List<T>.sum():T
    - 2개이상 제약을 걸어야하는 경우
      ```kotlin
      import java.time.Period
   
      fun <T> ensureTrailingPeriod(seq: T)
            where T : CharSequence, T : Appendable {
         if (!seq.endsWith('.')) {
            seq.append('.')
         }
      }
   
      fun main(args: Array<String>) {
         val helloWorld = StringBuilder("Hello World")
         ensureTrailingPeriod(helloWorld)
         println(helloWorld)
      }
      ```

4. Non-Null Type parameter 설정
    - 코틀린에서 타입의 기본은 Non null but, 제너릭인 경우에만 기본값이 Nullable
    - 명시적으로 nonNull 타입으로 선언해야하는 경우
   ```kotlin
   class Friend <T : Any> {
      fun getUniqueId(value: T) {
         value?.hashCode()   //null 처리가 필요하다
      }
   }

   fun main(string: Array) {
      val friend = Friend()
      friend.getUniqueId(null)  //가능
   }
   ```

## 실행시 동작
> 자바의 제네릭 -> JVM에서 Type Erasure
> 코틀린 -> inline으로 만들어 회피 가능
1. 실행시점의 제네릭
   - 자바와 동일하게 런타임에 타입 소거됨
   - 

2. Reified로 타입 실체화

3. 실체화한 타입으로 클래스 참조

4. Reified type parameter 제약 

## Variance

> 기저 타입 : Base Type (List), 타입 인자 : (Any, String)

1. Variance의 존재 이유
    - List<Any> 파라미터 타입을 가진 함수에 List<String>을 넘겼을때의 안정성 여부
    - String -> Any는 안전.. List<String> -> List<Any>는 안전하지 않음
        - [ ] 예제 실행 X
2. 클래스, 타입, 하위 타입
    - 타입과 클래스의 차이점
        - var x : String -> String 클래스의 인스턴스를 저장할수 있는 변수 -> String Type
        - var x : String? -> String + Null Type
        - List -> 타입 X, 클래스
        - List<Int>, List<String> -> 타입 -> Generic 클래스는 무수히 많은 타입을 만들수 있음
    - 하위 타입 / 상위 타입
        - Int -> Number (O)
            - Int는 Number의 하위 타입이고, Number는 Int의 상위 타입이다!!
            - Int는 Number의 하위 클래스이고, Number는 Int의 상위 클래스이다!!
        - Int -> String (X)
        - Null Type이 포함된 경우는??
            - Int -> Int? (O)
            - Int? -> Int (X)
      ```kotlin
      fun test(i: Int) {
         val n: Number = i 
         fun f(s:String) {
            println(s)
         }
         f(i) // 컴파일 에러 
      }
      ```
3. Covariant
    - 하위 타입을 유지하는 기법
    - Producer<Cat>은 Producer<Animal>의 하위 타입
    - out 키워드
    ```kotlin
    interface Producer<out T> { //클래스가 T에 대해서 공변적이라고 선언
        fun produce(): T
    }
   
    open class Animal {
      fun feed() {
        println("먹었다!!")
      }
    }
   
    fun feedAll(animals: Herd<Animal>) {
        for (i in 0 until animals.size) {
            animals[i].feed()
        }
    }
   
   class Cat: Animal() {
        fun cleanLitter() {
       }
   }
   
   fun takeCareOfCats(cats: Herd<Cat>) {
        for (i in 0 until cats.size) {
            cats[i].cleanLitter()
        }
        feedAll(cats)
   }
   
   //무리...
   class Herd<out T : Animal> { //Out 키워드를 활용하여, 하위 타입을 유지하도록 변경
        val size: Int
            get() {
                TODO()
            }
   
       operator fun get(i: Int): T {
            TODO()
        }
    }
    ```
4. Inconvariant
    - [ ] Comparator 예제가 바뀜..
    - in 키워드를 활용
   ```markdown
    expect fun interface Comparator<T> {
          fun compare(a: T, b: T): Int
    }
   ```

5. 사용 지점 변성
    - 타입이 언급될때 변성 지정
   ```java
   //java 와이드 카드를 이용한 사용 지점 변성
   <R> Stream<R> map(Function<? super T, ? extends R> mapper);
   ```
   ```kotlin
   fun <T> copyData(source : MutableList<T>, destination: MutableList<T>) {
     TODO()
   }
   
   fun <T> copyData(source : MutableList<T>, destination: MutableList<R>) {
     TODO()
   }
   
   fun <T> copyData(source : MutableList<out T>, destination: MutableList<T>) { 
     TODO()
   }
   
   //원본 리스트 원소 타입의 상위 타입을 대상 리스트 원소 타입으로 허용
   fun <T> copyData(source : MutableList<T>, destination: MutableList<in T>) {
     TODO()
   }
   ```
   > MutableList<out T> = List<? extends T> <br>
   > MutableList<in T> = List<? super T> <br>

6. "*"
    - 제네릭 타입 인자 정보 없을 표현 -> 타입 정보가 없거나 주요하지 않을때 사용
    - 의미
        - List<*> / List<Any> 의 의미는 같지 않음
       ```kotlin
       val list: MutableList<Any?> = mutableListOf('a', 1, "hello")
       val chars = mutableListOf('a', 'b', 'c')
       val unknownElements: MutableList<*> = if (Random.nextBoolean()) list else chars
       unknownElements.add(42) //The character literal does not conform to the expected type Nothing
       ```