# 7장 연산자 오버로딩과 기타 관례

```markdown
Convention : 연산자와 정해진 이름의 함수를 연결해주는 기법 println("3" + "4")
println(3+4)

//python println("3".__add__("4"))
println((3).__add__(4))
```

## 산술 연산자 오버로딩

1. 이항 산술 연산 오버로딩
    - "+" -> plus
    - "-" -> minus
    - "*" -> times
    - "/" -> div
    - "%" -> rem
    - "<<" -> shl
    - ">>" -> shr
    - ">>>" -> ushr
    - "&" -> and
    - "|" -> or
    - "^" -> xor
    - "~" -> inv

2. 복합 대입 연산자 오버로딩
    - "+=" -> plusAssign
    - "-=" -> minusAssign
    - "*=" -> timesAssign
    - "/=" -> divAssign
   ```markdown
   코틀린은 컬렉션에도 해당 연산지를 제공합니다. 단 아래 규칙에 따릅니다.
    1. +, - 는 항상 새로운 collection을 반환한다
    2. mutable collection에서 +=, -=는 collection을 원소를 변경한다.(새로운 collection을 생성하지 않음)
    3. 불변 collection에서 +=, -=는 새로운 collection을 반환한다. 따라서 이를 받는 변수는 var로 선언되어야 한다
   ```

3. 단항 연산자
    - "+a" -> unaryPlus
    - "-a" -> unaryMinus
    - "!a" -> not
    - "++a, a++" -> inc
    - "--a, a--" -> dec

## 비교 연산자 오버로딩

1. equals
    - "==" -> equals
    - "!=" -> equals
    - extension fuction 구현 불가
        - [ ] Any안에 operator 키워드가 붙어서 구현되어 있음. override keyword를 사용하여 == 와 치환 가능..
   ```markdown
   a == b -> a?.equals(b) ?: (b==null)
   ```

2. compareTo
    - <, >, <=, >= 는 Comparable의 compareTo 함수를 호출
   ```markdown
   a >= b -> a.compareTo(b) >= 0
   ```

## Collection과 Range의 Convention

1. Index
    - get
      ```kotlin
      data class Point(val x: Int, val y: Int) 
      
      operator fun Point.get(index: Int): Int { 
        return when(index) { 
           0 -> x 
           1 -> y 
           else -> throw IndexOutOfBoundsException("Invalid coordinate $index") 
        } 
      } 
      
      fun main(args: Array) { 
        val p = Point(10, 20) 
        println(p[1]) //20 
      }
      ```
      > x[a, b] -> x.get(a, b)
    - set
      ```kotlin
      data class MutablePoint(var x: Int, var y: Int) 
      
      operator fun MutablePoint.set(index: Int, value: Int) { 
        when(index) { 
            0 -> x = value 
            1 -> y = value 
            else -> throw IndexOutOfBoundsException("Invalid coordinate $index") 
        } 
      } 
      
      fun main(args: Array) { 
        val p = MutablePoint(10, 20) 
        p[1] = 42 
        println(p) //42 
      }
      ```
      > x[a, b] = c -> x.set(a ,b, c)

2. "in"
    - "cotains" 함수와 연결
      ```kotlin
      data class Point(val x: Int, val y: Int) 
      data class Rectangle(val upperLeft: Point, val lowerRight: Point) 
      
      operator fun Rectangle.contains(p: Point): Boolean { 
         return p.x in upperLeft.x until lowerRight.x 
                && p.y in upperLeft.y until lowerRight.y 
      } 
      
      fun main(args: Array) { 
       val rect = Rectangle(Point(10, 20), Point(50, 50)) 
       println(Point(20, 30) in rect) 
       println(Point(5, 5) in rect) 
      }
      ```
      > a in c -> c.contains(a)

3. "rangeTo"
    - ".." 
      ```kotlin
      fun main(args: Array) { 
        val n = 9 
        println(0..(n + 1))  
        (0..n).forEach { 
            print(it) //0~9
        } 
      }
      ```
      > start..end -> start.rangeTo(end)

4. "iterator"
   ```kotlin
   import java.util.Date 
   import java.time.LocalDate 
   
   operator fun ClosedRange.iterator(): Iterator = 
        object : Iterator { 
            var current = start 
            override fun hasNext() = current <= endInclusive 
            override fun next() = current.apply { 
                current = plusDays(1) 
            }
         }
    
   fun main(args: Array) { 
        val newYear = LocalDate.ofYearDay(2017, 1) 
        val daysOff = newYear.minusDays(1)..newYear 
        for (dayOff in daysOff) { 
            println(dayOff) 
        } 
   }
   ```

## 구조 분해 선언(Destruction declaration)과 Component 함수

## Delegate

## 결론

```markdown
코틀린을 사용하고 싶은 이유를 찾은 듯...

1. null safe
2. extension fuction
```
