package entity


const val CREDIT_CARD_PASSWORD_LENGTH = 4
const val CREDIT_CARD_NUMBER_REGEX = "[0-9]+"

class CreditCard {

    var credit = 10000.00
    private var creditCardPassword: String = ""


    fun creditCardPassword(creditCardPassword: String): Boolean {
        fun validCreditCardPassword(creditCardPassword: String): Boolean {
            return if (!creditCardPassword.matches(CREDIT_CARD_NUMBER_REGEX.toRegex())) {
                println("The credit card password must have only numbers")
                false
            } else if (creditCardPassword.length < CREDIT_CARD_PASSWORD_LENGTH || creditCardPassword.length > CREDIT_CARD_PASSWORD_LENGTH) {
                println("The credit card password must have $CREDIT_CARD_PASSWORD_LENGTH numbers")
                false
            } else {
                this.creditCardPassword = creditCardPassword
                true
            }

        }

        return validCreditCardPassword(creditCardPassword)
    }

    open fun payment(amount: Double): Boolean {
        return if (credit > amount) {
            this.credit -= amount
            true
        } else {
            false
        }
    }

    open fun paymentCash(pay: Double, amount: Double): Boolean {

        var payCash = pay

        return if (payCash > amount) {
            payCash -= amount
            true
        } else {
            false
        }
    }
}