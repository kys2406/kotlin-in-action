## 애노테이션과 리플렉션
>애노테이션 : 라이브러리가 요구하는 의미를 클래스에게 부여..
><br>리플렉션 : 실행 시점에 컴파일러 내부 구조 분석..
-------
어노테이션 만들기는 어렵다.. 테스트도 어려움..

## 애노테이션 선언과 적용

1. 애노테이션 적용
    - Deprecated 활용
        - replaceWith를 활용하여, 대신할 수 있는 패턴 제시
    - 자바와 같은 방법으로 애노테이션 적용 가능
        - @MyAnnotation(MyClass::class)처럼 ::class를 클래스 이름 뒤에 넣어야 함
        - 다른 애노테이션을 인자로 지정할 때는 인자로 들어가는 애노테이션 이름 앞에 @를 넣지 말아야함
        - 배열을 인자로 지정하려면 arrayOf 함수를 사용해야함
        - 컴파일 타임에 인자를 알 수 있어야함(const)

2. 애노테이션 대상
    - 자바에 선언된 애노테이션을 사용해 프로퍼티에 애노테이션을 붙이는 경우 사용 시점 대상(use-site target) 선언으로 애노테이션을 붙일 요소를 정할 수 있음
      ```kotlin
      @get:MyAnnotation
      val temp = Temp()
      ``` 

    - 사용 시점 대상 지정 목록
        - property : 프로퍼티 전체, 자바에서 선언된 애노테이션에는 이 사용 지점 대상을 사용할 수 없다.
        - field : 프로퍼티에 의해 생성되는 필드
        - get : 프로퍼티 게터
        - set : 프로퍼티 세터
        - receiver : 확장 함수나 프로퍼티의 수신 객체 파라미터
        - param : 생성자 파라미터
        - setparam : 세터 파라미터
        - delegate : 위임 프로퍼티의 위임 인스턴스를 담아둔 필드
        - file : 파일 안에 선언된 최상위 함수와 프로퍼티를 담아두는 클래스

    - 자바 API를 애노테이션으로 제어하기
        - @JvmName
            - 코틀린 선언이 만들어내는 자바 필드나 메소드 이름을 변경한다.
        - @JvmStatic
            - 메소드, 객체 선언, 동반 객체에 적용하면 그 요소가 자바 정적 메소드로 노출된다.
        - @JvmOverloads
            - 디폴트 파라미터 값이 있는 함수에 대해 컴파일러가 자동으로 오버로딩한 함수를 생성해준다.
        - @JvmField
            - 프로퍼티에 사용하면 게터나 세터가 없는 공개된 자바 필드로 프로퍼티를 노출시킨다.

3. 애노테이션 선언
    - 명시적으로 "annotation" 지시어 사용
   ```kotlin
   annotation class MyAnnoation
   annotation class MyAnnotation(val value: String) //명시적으로 value 사용
   ```

4. 매타애노테이션
    - 애노테이션에 애노테이션을 적용하는 것
   ```kotlin
   @Target(AnnotationTarget.ANNOTATION_CLASS) //Annotaion을 직접 만들때
   @Retention(RetentionPolicy.RUNTIME) //기본값

   /**
   * Contains the list of code elements which are the possible annotation targets
    */
   public enum class AnnotationTarget {
       /** Class, interface or object, annotation class is also included */
       CLASS,
       /** Annotation class only */
       ANNOTATION_CLASS,
       /** Generic type parameter (unsupported yet) */
       TYPE_PARAMETER,
       /** Property */
       PROPERTY,
       /** Field, including property's backing field */
       FIELD,
       /** Local variable */
       LOCAL_VARIABLE,
       /** Value parameter of a function or a constructor */
       VALUE_PARAMETER,
       /** Constructor only (primary or secondary) */
       CONSTRUCTOR,
       /** Function (constructors are not included) */
       FUNCTION,
       /** Property getter only */
       PROPERTY_GETTER,
       /** Property setter only */
       PROPERTY_SETTER,
       /** Type usage */
       TYPE,
       /** Any expression */
       EXPRESSION,
       /** File */
       FILE,
       /** Type alias */
       @SinceKotlin("1.1")
       TYPEALIAS
   }
   ```

## 리플렉션
	- 실행 시점에 객체의 프로퍼티와 메소드에 접근 할 수 있게 해주는 기법
	- 필요한 이유
		- 타입과 관계 없이 객체를 다뤄야 하거나 객체가 제공하는 메소드나 프로퍼티 이름을 오직 실행 시점에만 알 수 있는 경우
	- 사용법
		1. java.lang.reflect 패키지의 자바 표준 리플렉션
		2. kotlin.reflect 패키지의 코틀린 리플렉션
			- 자바의 기능 완벽 대체 불가		

1. 코틀린 리플렉션 API(KClass, KCallable, KFunction, KProperty)
    - KClass
        - KClass는 자바의 java.lang.Class에 해당하는 클래스
      ```kotlin
      val person = Person("Alice",27)
      val kClass = person.javaClass.kotlin
      println(kClass.simpleName)
      
      import kotlin.reflect.full.memberProperties
      kClass.memberProperties.forEach { println(it.name) }
      ```

    - KCallable
        - 함수와 프로퍼티를 아우르는 공통 상위 인터페이스
        - call 메소드가 존재하며 이 call을 사용하면 함수나 프로퍼티의 게터를 호출
      ```kotlin
      fun foo(x:Int) = println(x) 
      //컴파일 : public fun foo(x: kotlin.Int): kotlin.Unit { /* compiled code */ }
      //

      val kFunction = ::foo  //KFunction클래스의 인터페이스
      kFunction.call(42)
      ```

    - KFunction
        - ::foo의 값 타입이 리플렉션 API에 존재하는 KFunction 클래스의 인스턴스
        - invoke 메소드는 호출할 때 인자 개수나 타입이 맞아 떨어져야만 컴파일이 됨
      ```kotlin
      import kotlin.reflect.KFunction2

      val kFunction2: KFunction2<Int, Int, Int> = ::sum
      println(kFunction2.invoke(1, 2) + kFunction2.invoke(3, 4))

      fun sum(x: Int, y: Int) = x + y
      ```
    - KProperty
      ```kotlin
      var counter = 0
      val kProperty = ::counter
      kProperty.setter.call(21)
      println(kProperty.get())
      ```


## DSL 만들기
> Domain Specific Lanageage
- 일반 언어 : 명령적(imperative)
    - "정확히" 순서 기술
- DSL : 선언적(declarative)
    - 결과에 필요한 내용만 기술하고, 실제 세부 실행은 엔진에 맡김

1. Internal DSL
	- 범용 언어로 작성된 프로그래밍의 일부이며 범용 언어와 동일한 문법을 사용하여, DSL 역할 및 객체의 매핑 작업 등을 자동으로 처리
	- Mybatis / Hibernate / JPA / Exposed 기타등등

2. Invoke Convenstion
```kotlin
class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!")
    }
}

val bavarianGreeter = Greeter("Servus")
bavarianGreeter("Dmitry")
```
