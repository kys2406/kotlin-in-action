# 5장 람다로 프로그래밍

## 람다

```markdown
다른 함수에 넘길 수 있는 작은 코드 조각... AWS Lambda도 이와 유사한 개념으로 이름을 지은듯..
안드로이드는 N OS(2016.08 출시) 이후 부터 가능
```

## 람다 식과 멤버 참조

1. 람다 소개

```
   button.setOnClickListener(new onClickListener(){
      @Override
      public void onClick(View view){
        //TODO    
      }
   });

   button.setOnClickListener(view->{
        //TODO
   });
```

2. 람다와 컬렉션
    - 코드 중복 제거!!
       ```kotlin
       val people = listOf(Person("Alice", 29), Person("Bob", 31))
       println(people.maxByOrNull { it.age })
       ```
3. 람다 식의 문법
   ```kotlin
   val sum = { x: Int, y: Int -> x + y }
   println(sum(1, 2))
   ```
    - "it" : 람다식 default parameter name..
        - 위 예제 참고
        - but.. 남용하지 말것..

4. 현재 영역에 있는 변수에 접근
    - Final 변수가 아닌 변수에 접근 가능!!(자바와의 차이점)
    - Lambda Capture Variable : 람다 안에서 사용하는 외부 변수
    - [X] java와의 차이점 + 이해 안되는 부분 살펴볼 필요있음...
       - java는 외부 변수를 사용하려면 final로 명시해야함(Stack 영역 안에 로컬 변수의 메모리 생성 후 함수 소멸이후 Stack 이 없어짐...)
       - kotlin은 Lambda Capturing(변수 값 복사) 후 Lambda 내부에서 사용

5. 멤버 참조
    - "::" -> 멤버 참조
      ```kotlin
      val getAge = Person::age
      
      val getAge = {person: Person -> person.age}
      
      people.maxByOrNull(Person::age)
      people.maxByOrNull{ p -> p.age }
      people.maxByOrNull{ it.age }
      
      fun salute() = println("Salute!!")
      run(::salute)
      ```

## 컬렉션 함수형 API

1. filter / map
   - Filter : 조건에 맞는 Filtering
   - Map : 원하는 형태로 변환
      - filterKeys, mapKeys
      - filterValues, mapValues
   ```kotlin
   fun main(args: Array) { 
      val list = listOf(1, 2, 3, 4) 
      //짝수만 출력
      println(list.filter { it % 2 == 0 }) 
   }
   
   data class Person(val name: String, val age: Int) 
   
   fun main(args: Array) { 
      val people = listOf(Person("Alice", 29), Person("Bob", 31)) 
      //people의 name만 변환
      println(people.map { it.name }) 
   }
   
   ```
2. all, any, count, find
   - all : Collection전체가 Predicate 만족 여부 판단
   - any : Collection 하나라도 Predicate 만족 여부 판단
   - count : Predicate 만족하는 Element의 개수
      - [ ] Filter + Size의 조합과의 메모리 비교해볼 필요있음
   - find : Predicate 만족하는 첫번째 Element 반환
3. groupBy : Grouping...
4. flatmap / flatten
    - flatmap : List<List> -> List~~~~
    - [X] flatten

## 시퀀스 : 지연 컬렉션 연산
   - filter / map 호출시 새로운 List 반환 
      - 최종 결과물이 아닌 Chaining을 반복해서 사용할 경우에는 새로운 리스트가 생김
      - 자바 Stream과의 차이점!!
      - 한번에 연산되게 하고 싶다면 asSequence 이용 -> Element가 많으면 훨씬 좋은 성능 만들수 있음
      ```kotlin
      listOf(1, 2, 3, 4).asSequence() 
         .map { print("map($it) "); it * it } 
         .filter { print("filter($it) "); it % 2 == 0 }
         .toList()
      ```
   - 시퀀스
      ```kotlin
      val naturalNumbers = generateSequence(0) { it + 1 } //range / rangeClosed와 동일
      val numbersTo100 = naturalNumbers.takeWhile { it <= 100 } 
      println(numbersTo100.sum())
      ```

## 자바 함수형 인터페이스를 코틀린에서 사용

1. 자바 메소드에 람다를 인자로 전달
   - functinal interface(Runnable / Callable....)을 호출해서 바로 사용..
2. SAM(Single Abstract Method) 생성자
   - 컴파일러가 람다식을 자바의 Funcational Interface로 자동으로 변환하는 함수
   - https://kotlinlang.org/docs/java-interop.html#sam-conversions
   ```kotlin
   fun createAllDoneRunnable(): Runnable { 
      return Runnable { println("All done!")
      } 
   } 
      
   fun main(args: Array) { 
      createAllDoneRunnable().run() 
   }

   ```

## 수신 객체 지정 람다 사용

1. with
   ```kotlin
   fun alphabet(): String { 
      val stringBuilder = StringBuilder() 
      return with(stringBuilder) {  //stringBuild + lambda 가 parameter...
         for (letter in 'A'..'Z') { 
            this.append(letter) //this로 수신객체를 표현 
         } 
         append("\nNow I know the alphabet!") // this 없이도 호출 가능 
         this.toString() //Return값
      } 
   } 
      
   fun main(args: Array) { 
      println(alphabet()) 
   }
   
   
   class OuterClass { 
      fun alphabet() = with(StringBuilder()) { 
         for (letter in 'A'..'Z') { 
            append(letter)
         } 
         append("\nNow I know the alphabet!")    
         println(this@OuterClass.toString()) //람다 내부에서 외부 Class 이름 호출시
         toString() 
      } 
   } 
   
   fun main(args: Array) { 
      println(OuterClass().alphabet()) 
   }

   ```
2. apply
   - with와 거의 동일 
   - 객체의 확장함수로 동작
   - 객체 자기 자신이 Return됨(paramter가 2개일 필요없음)
   ```kotlin
   fun alphabet() = StringBuilder().apply {
      for (letter in 'A'..'Z') {
        append(letter)
      }
      append("\nNow I know the alphabet!")
   }.toString()

   fun main(args: Array) {
       println(alphabet())
   }
   ```

## 결론

```
람다 문법은 차이가 거의 없다고 볼정도로 자바와 비슷함...
```
