# 3장 함수 정의와 호출

## 컬렉션

1. 사용법
    ```
    val set = hashsetof(1, 7, 53)
    val map = hashMapof(1 to "one", 7 to "seven", 53 to "fifty-three") // to는 함수...
    ```
2. 특징
    - 자바 컬렉션을 그대로 사용 -> 호환성
    - 코틀린에서 제공하는 추가적인 함수(ex. max() / last())

## 함수

1. 이름 붙인 인자
   ```
   joinToString(list, separator = ", ", prefix = "(", postfix = ")")
   ```
2. 디폴트 파라미터 값
   ```
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
   ```   
    - @JvmOverloads 옵션
        - 자바에서 디폴트 파라미터가 있는 코틀린 함수를 사용할때 사용
   ```
   //Java
   _2_InitKt.joinToString(tests);
   _2_InitKt.joinToString(tests, ",");
   _2_InitKt.joinToString(tests, ",", "(");
   _2_InitKt.joinToString(tests, ",", "(", ")");
   ```

3. 최상위 함수 / 프로퍼티
    - public static -> func
    - @file:JvmName
    - 최상위 프로퍼티

## 확장 함수 / 프로퍼티
- 자연스러운 통합!!
   1. 확장함수
      ```
      //receiver type
      fun String.lastChar() : Char = this.get(this.length - 1)
      
      // receiver object
      import strings.lastChar
      println("Kotlin".lastChar())
      ```
   2. Import / Extension Method
      ```
      import strings.lastChar as last
      println("Kotlin".last())   
      ```
   3. 확장 함수로 유틸리티 함수 정의
      ```
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
      ```
  4. 확장 함수는 오버라이드 불가!!
      - 확장 함수는 클래스 밖에서 선언되기 때문에..... 동적으로 결정되지 않음!!
      - 우선순위 : 멤버 함수 > 확장 함수
  5. 확장 프로퍼티 

## 컬렉션 처리
1. 자바 API의 확장 함수!!
   - last
   - max
2. 가변 인자 함수
   - java : "..." -> kotlin : "*args"
   - 별로 좋아하지 않는 문법..
3. 중위 호출 / 구조 분해 선언
   ```
   val map = hashMapOf(1 to "one", 7.to("seven"), 53 to "fifty-three")
   7.to("seven") //일반적인 방식 
   1 to "one"  //중위 호출 방식
   
   val (number, name) = 1 to "one"
   println(number)
   println(name)
   
   for ((index, element) in collection.withIndex()) {
   ..... 
   }
   ``` 

## 문자열과 정규식다루기
1. split 
2. 정규식
    ```
    fun parsePath(path: String) {
        val regex = """(.+)/(.+)\.(.+)""".toRegex()
        val matchResult = regex.matchEntire(path)
        if (matchResult != null) {
            val (directory, filename, extension) = matchResult.destructured
            println("directory : $directory, filename : $filename, extension : $extension")
        }
    }
    ```
3. 여러중 3중 따옴표 문자열..
    ```
    val kotlinLogo =
        """| //
           .| // 
           .|/ \""".trimMargin(".")

    println(kotlinLogo)
    ```
## 코드 중복 제거(로컬함수)
- User.kt 참고...  

## 결론

```
코틀린의 함수 관련 여러 기법 등에 대해서 공부했음
Scala와 동일하게 확장, 로컬함수의 기능을 제공해주는 것은 엄청 좋음!!
또한, String 관련 여러 기능 StringTemplate, Multiline 처리등에 대해서도 장점이 보임
다만, 객체 캡슐화 관련 기능에 대해서는 장점이 있지만, 코드를 분석할때에는 분석하는데에 어려움이 있을것으로 예상됨...
```