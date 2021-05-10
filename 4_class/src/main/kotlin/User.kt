interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribeingUser(val email: String) : User {
    override val nickname: String
        get() = TODO("Not yet implemented")
}

class FacebookUser(val accountId: Int) : User {
    override val nickname: String
        get() = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int): String {
        TODO("Not yet implemented")
    }
}