# 5장 람다로 프로그래밍

## 람다 뜻

```markdown
다른 함수에 넘길 수 있는 작은 코드 조각... AWS Lambda도 이와 유사한 개념으로 이름을 지은듯..
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
    - Final 변수가 아닌 변수에 접근 가능!!
    - Capture Variable : 람다 안에서 사용하는 외부 변수
    - [ ] java와의 차이점 + 이해 안되는 부분 살펴볼 필요있음...

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
    -

## 컬렉션 함수형 API

1. filter / map
2. all, any, count, find
3. groupBy
4. flatmap / flatten
    - [ ] flatten

## 시퀀스 : 지연 컬렉션 연산

1. 중간 연산 / 최종 연산
    - 중간 연산
    - 최종 연산
2. 시퀀스

## 자바 함수형 인터페이스를 코틀린에서 사용

1. 자바 메소드에 람다를 인자로 전달
2. SAM(Single Abstract Method) 생성자
    - https://kotlinlang.org/docs/java-interop.html#sam-conversions

## 수신 객체 지정 람다 사용

1. with
2. apply

## 결론

```
람다 문법은 차이가 거의 없다고 볼정도로 자바와 비슷함...
```