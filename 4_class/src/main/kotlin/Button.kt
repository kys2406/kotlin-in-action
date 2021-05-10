class Button : Clickable, Focusable, Clickable2 {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff() //상위 타입의 메소드 호출 지정 방법...
        super<Focusable>.showOff()
        // Super calls to Java default methods are prohibited in JVM target 1.6. Recompile with '-jvm-target 1.8'
        // super<Clickable2>.showOff()
    }
}