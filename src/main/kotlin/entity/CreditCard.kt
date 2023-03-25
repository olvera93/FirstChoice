package entity

class CreditCard {

    var credit = 10000.00

    open fun payment(amount: Double): Boolean{
        return if (credit > amount){
            this.credit -= amount
            true
        }else{
            false
        }
    }
}