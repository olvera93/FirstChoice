package entity

private const val MIN_PASS_LENTH = 6
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
private const val MIN_USER_LENTH = 3

class User {

    private var user: String = ""
    private var password: String = ""
    private val order: MutableMap<String, FoodMenu> = mutableMapOf()

    fun getUser(): String {
        return user
    }

    fun signIn(user: String, password: String): Boolean {
        fun validateInputs(userInput: String, passwordInput: String): Boolean {
            return if (userInput.isEmpty() || passwordInput.isEmpty()) {
                println("User or password is empty")
                false
            } else if (userInput.length < MIN_USER_LENTH) {
                println("The user must have at least $MIN_USER_LENTH characters")
                false
            } else if (passwordInput.length < MIN_PASS_LENTH) {
                println("The password must have at least $MIN_PASS_LENTH characters")
                return false
            } else if (!passwordInput.matches(PASS_PATTERN.toRegex())) {
                println("The password must have at least one uppercase, one lowercase and one number")
                return false
            } else if (userInput == this.user && passwordInput == this.password) {
                println()
                println("**********************")
                println("\tWelcome $user")
                println("**********************")
                println()

                true
            } else {
                println("User or password is wrong")
                false
            }
        }

        return validateInputs(user, password)
    }

    fun signUp(user: String, password: String) {
        if (user.isEmpty() || password.isEmpty()) {
            println("User or password is empty")
        } else if (user.length < MIN_USER_LENTH) {
            println("The user must have at least $MIN_USER_LENTH characters")
        } else if (password.length < MIN_PASS_LENTH) {
            println("The password must have at least $MIN_PASS_LENTH characters")
        } else if (!password.matches(PASS_PATTERN.toRegex())) {
            println("The password must have at least one uppercase, one lowercase and one number")
        } else {
            this.user = user
            this.password = password
            println()
            println("Successful sign up")
        }
    }

    fun addOrder(food: FoodMenu) {
        order[food.id.toString()] = food
    }

    fun getOrder(): Map<String,FoodMenu> {
        return order
    }

}