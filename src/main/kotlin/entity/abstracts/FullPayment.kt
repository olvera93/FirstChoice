package entity.abstracts

import entity.MethodPayment
import entity.User

abstract class FullPayment {

    abstract fun totalBill(user: User): Double

    abstract fun payBill(user: User, listMethodPayment: MethodPayment)

}