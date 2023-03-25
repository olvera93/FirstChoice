package entity

enum class MethodPayment(val method: String) {
    CASH("Cash"),
    CREDIT_CARD("Credit card"),
    PAYPAL("PayPal")
}