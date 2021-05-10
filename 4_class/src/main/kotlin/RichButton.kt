open class RichButton : Clickable {
    fun disable() { //Final-> Override 불가!!
        println("Diable...")
    }

    open fun animate() { //Override 가능
        println("Animate...")
    }

    override fun click() { //Override 메소드는 기본적으로 Open 상태
    }
}