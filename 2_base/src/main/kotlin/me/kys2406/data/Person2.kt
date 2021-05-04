package me.kys2406.data

/**
 * 코틀린
 */
class Person2(val name: String, // 읽기 전용 프로퍼티, Getter 제공
              var isMarried: Boolean //쓸수 있는 프로퍼티, Getter / Setter 제공
)