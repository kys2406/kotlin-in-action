# 2장 코틀린 기초

## 함수, 변수

1. Hello, World
2. 함수
3. 변수
    - 선언방법
      ```
      val question = "질문??"
      val answer = 42
      val answer2: Int = 42
      val yearsToCompute = 7.5e6
      
      //초기화를 나중에 하려면 변수타입을 명시해야함..
      val answer3: Int
      answer = 42
      
      ```
    - val(value) / var(variable)
4. 문자열 템플릿

## 클래스와 프로퍼티

1. 프로퍼티
    - Person / Person2
2. 커스텀 접근자
3. 디렉토리 / 패키지
    - 자바 : 일치해야함
    - 코틀린 : 일치할 필요없음(오오!!!!)

## 선택 표현과 처리

1. enum
2. when
    - switch-case와 유사
    - 기능은 더 좋은거 같음
    - if else 구문을 캐스팅하는 것 살펴보기!!
3. 스마트 캐스트
    - is를 활용한 변수 타입 검사 -> instanceof
    - instanceof 이후의 원하는 타입으로 컬파일러가 캐스팅을 수행하는 행위 -> 스마트 캐스트

## While / For-loop

- while / do-while 자바와 동일
- for-loop
```
    for (i in 100 downTo 1 step 2) {
        println(_3_6_9(i))
    }

    for (i in 0 until 100) {
        println(_3_6_9(i))
    }

    for (i in 0..99) {
        println(_3_6_9(i))
    }
```

## 예외처리
- 예외 발생
  ```
  throw IllegalArgumentExcpetion("Error")
  ```
- try, catch, finally
  ```
  try {
      ~~~~~
  } catch(e: Exception) {
      ~~~~
  } finally {
      ~~~~
  }
  ```
- 체크 예외, 언체크 예외 구분 X
  ```
  //IOException (Checked Exception)
  
  //Java
  public String readData(Reader reader) throws IOException {
     .....
  }
  
  //Kotlin
  fun readData(reader: Reader): String? {
     .....
  }
  ```
- try를 식으로..
    ```
    val number = try {
        Integer.parseInt(reader.readerLine())
    } catch(e: NumberFormatException) {
        null
    }
    //예외 발생시 number는 null을 사용함
    println(number)
    ```

## 결론
```
함수 선언, 클래스 선언 방법 등에 대해서 보았음.
자바와 유사한 부분도 있으나 선언 및 초기화 부분에서 최신 언어의 패턴을 보이는 경향이 있음
when은 switch-case에 있었으면 했던 기능이 다 들어가 있는것으로 보임.. 잘쓰면 좋을듯...
foreach 구문의 답답함을 한방에 해결해주는 듯...ㅎㅎ 
예외 처리도 최근 언어의 경향을 보이는 것 같으나, Checked / Unchecked Exception을 명확히 구분해주는 자바가 더 좋은거 같음....
```