package entity.abstracts

import entity.User

abstract class FullPayment {

    abstract fun fullPayment(user: User): Double

}