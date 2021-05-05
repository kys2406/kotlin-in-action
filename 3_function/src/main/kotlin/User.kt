import java.lang.IllegalArgumentException

class User(val id: String, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("empty name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException("empty name")
    }
}

fun saveUser_2(user: User) {
    fun validate(user: User, value:String, fieldName:String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("empty ${user.id}, $fieldName")
        }
    }
    validate(user, user.name, "name")
    validate(user, user.address, "address")
}

fun saveUser_3(user: User) {
    fun validate(value:String, fieldName:String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("empty ${user.id}, $fieldName")
        }
    }
    validate(user.name, "name")
    validate(user.address, "address")
}

//로컬함수 구현
fun User.valdateBeforeSave() {
    fun validate(value:String, fieldName:String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("empty ${id}, $fieldName")
        }
    }
    validate(name, "name")
    validate(address, "address")
}

fun saveUser_4(user: User) {
    user.valdateBeforeSave()
}