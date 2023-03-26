package utils

fun paymentProcessed() {
    print("Your payment is being processed ")
    val iteration = 5
    val anim = listOf(".", "..", "...", "....")
    var i = 0
    while (i < iteration) {
        print(anim[i % anim.size])
        Thread.sleep(2_000L)
        print("\b")
        print(" ")
        print("\b")
        i++
    }
}