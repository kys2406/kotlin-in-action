# 6장 코틀린 타입 시스템

## null Type

1. "?"
    - null이 될 수 있는 타입
        - Java -> null 가능(Default)
        - Kotlin -> null 불가(Default)
            - '?'로 null 가능 여부 명시!!
    ```java
    //java
    public int getLen(String str) { 
        return str.lengh(); 
    }
    ```
   
    ```kotlin
    //kotlin
    fun getLen(str: String)  = str.length // Null 불가
    fun strLenSafe(s: String?): Int = if (s != null) s.length else 0 //Null 가능
    ```

2. "?."
    - null safe operator
    - null 검사 + 메소드 호출
    ```kotlin
    s?.toUppercase()
   
    val country = this.company?.address?.country //연속 사용 
    return if (country != null) country else "Unknown"
    ```
3. "?:"
    - Elvis Operator
    - 없는 경우.. Default값 지정
   ```kotlin
   val name = str ?: "Unknown"
   
   //company 정보가 없으면 exception 강제 발생
   val address = person.company?.address 
        ?: throw IllegalArgumentException("No address")  
   with (address) { 
        println(streetAddress) 
        println("$zipCode $city, $country") 
   }
   ```

4. "as?"
    - Safe Cast
    - ClassCastException 방지를 위한 변환 방법
    - as? 는 casting을 시도하고, casting이 불가능 하면 null을 반환

5. "!!"
    - 반드시 null이 아님

6. let 함수
    - not null인 경우 특정 구문을 수행하고 싶을때 사용
7. "lateinit"
    - Property 초기화 지연..
   ```kotlin
   class MyService { 
        fun performAction(): String = "foo" 
   } 
   
   class MyTest { 
        private lateinit var myService: MyService 
        @Before 
        fun setUp() { 
            myService = MyService() 
        } 
   
        @Test 
        fun testAction() { 
            Assert.assertEquals("foo", myService.performAction()) 
        } 
   }
   ```


8. nullable type의 extension function
    - nullable type에서는 그 기능에 필요한 확장함수를 제공
      ```kotlin
      fun verifyUserInput(input: String?) {
          if (input.isNullOrBlank()) { //String? 타입의 extension function
              println("Please fill in the required fields")
          }
      }
      fun main(args: Array) {
          verifyUserInput(" ") verifyUserInput (null)
      }
      ```

9. Generic의 Nullable

10. 플랫폼 타입
   - [ ] 다시보기...
   ```markdown
   - 자바와 연동에 있어 자바는 nullable type이 default
   - 이것으로 인한 kotlin과의 연동에 문제가 될 수 있기 때문에 이런 타입을 플랫폼 타입으로 처리
   - 플랫폼 타입은 "!" 로 컴파일러 내부에서 표현 되지만, 직접 개발자는 코트상에서 플랫폼 타입을 직접 선언불가
   ```

## Kotlin Primitive Type

1. Int / Boolean
2. Int? / Boolean?
3. 숫자변환
    - toLong() / toShort() / toChar()....
    - 원시 타입 리터럴
        - Long -> L
        - Float -> F, f
        - 16진수 -> 0x, 0X
        - 2진수 -> 0B, 0b
    - 문자열 숫자변환
    ```kotlin
    println("42".toInt()) //실패시 NumberFormatException...
    ```
4. Any, Any?
    - 모든 객체의 최상위 객체(컴파일시 Object로 변환..)
    - wait(), notify() 미지원
    ```markdown
    wait()/notify() 미지원 이유??
   
    Effective Java Item 69 kindly suggests to prefer concurrency utilities 
    to wait() and notify(). 
    Thus, these methods are not available on references of type Any.
    ```
5. Unit
    - void : 리턴값없음
    - Unit : 리턴값 없음 + 인자로도 사용 가능
        - return을 넣지 않아도 컴파일러가 묵시적으로 return을 넣어줌

6. Nothing
    - 함수가 정상적으로 끝나지 않는다라는걸 명시적으로 표현
    ```kotlin
    fun fail(message: String): Nothing { 
        throw IllegalStateException(message) 
    } 
   
    fun main(args: Array) {     
        fail("Error occurred") 
    }
   
    fun getCompany(person: Person) { 
        val comp = Person.company ?: fail("No company info.")
        return comp.name 
    }
    ```    

## 컬렉션과 배열

1. 컬렉션에서의 Null처리
    - List<Int> : list -> null불가, 원소 -> null불가
    - List<Int?> :list-> null불가, 원소 -> null가능
    - List<Int>? : list-> null가능, 원소 -> null불가
    - List<Int?>? : list-> null가능, 원소 -> null가능

2. Immutable / Mutable
    - Default : Immutable
    - 수정이 필요한 경우에만 MutableCollection 사용

3. Java와의 연결
    - 읽기 전용: listOf, setOf, mapOf
    - 변경 가능
        - mutableListOf, arrayListOf
        - mutableSetOf, hashSetOf, sortedSetOf, linkedSetOf
        - mutableMapOf, hashMapOf, linkedMapOf,sortedMapOf
    - 주의사항!!
        - kotlin에서는 immutable이더라도 java에서 사용할때에는 mutable임
        - kotlin에서 원소 null 불가더라도, java에서는 원소 null 가능
4. 컬렉션을 플랫폼 타입으로 다루기
    - Java에서 정의된 컬렉션을 코틀린에서 사용하면 플랫폼 타입으로 변경됨
    - 용도에 따라서 선택!!
        - 컬렉션 혹은 원소의 Null 여부... override하는 메서드의 변경 여부에 따라 선택...
5. 객체의 배열과 원시타입의 배열
    - arrayOf(원소1,원소2,...)
    - arrayOfNulls(개수) : 해당 개수만큼 null을 넣어 배열 생성
    - Array(개수, 생성식-lambda): 해당 개수만큼 주어진 람다를 이용해서 배열 생성

## 결론

```markdown
코틀린을 사용하고 싶은 이유를 찾은 듯...
1. null safe
2. extension fuction
```
