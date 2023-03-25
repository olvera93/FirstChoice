package utils

import entity.*
import entity.abstracts.FullPayment
import interfaces.ShowOrder
import java.lang.Thread.sleep
import java.time.LocalDate
import java.time.LocalTime

class Menu : FullPayment(), ShowOrder {

    private var exit = false
    private lateinit var dishes: Map<String, FoodMenu>

    suspend fun showMenu(user: User, userList: MutableList<User>) {
        do {
            println()
            val listOfSettings = Settings.values().toList()
            listOfSettings.forEachIndexed{ index, settings ->
                println("${index + 1}. ${settings.option}")
            }

            when (validateInput("Int", "Select an option: ")) {
                1 -> {

                    println()
                    println("You choose Sign in")
                    println()
                    print("User: ")
                    val userSignIn = readlnOrNull() ?: ""

                    print("Password: ")
                    val passwordSignIn = readlnOrNull() ?: ""

                    if (user.signIn(userSignIn, passwordSignIn)) {

                        do {
                            println()
                            val listOfCategory = Category.values().toList()
                            println(
                                "    _____       _          \n" +
                                        "   |     |     | |         \n" +
                                        "   |     | __ _| |_ ___    \n" +
                                        "   |     |/ _` | __/ _ \\   \n" +
                                        "   |_____| (_| | ||  __/   \n" +
                                        "         \\__,_|\\__\\___|   \n" +
                                        "          ___________      \n" +
                                        "        //          \\\\   \n" +
                                        "       //            \\\\ \n" +
                                        "      //______________\\\\\n" +
                                        "      |                |\n" +
                                        "      |                |\n" +
                                        "      |      MENU      |\n" +
                                        "      |                |\n" +
                                        "      |                |\n" +
                                        "      |                |\n" +
                                        "      |________________|\n"
                            )

                            listOfCategory.forEachIndexed { index, category ->
                                println("${index + 1}. ${category.foodCategory}")
                            }
                            println("5. Show my order")

                            println()

                            when (validateInput("Int", "Select an option: ")) {
                                1 -> {
                                    println()
                                    dishes = foodList.filter { it.value.category == Category.STARTERS }
                                    dishes.toList().forEachIndexed { index, entry ->
                                        val (key, value) = entry
                                        println(
                                            """${index + 1}. ${value.name}
                                            |Price: ${value.price}
                                            |Description: ${value.description}
                                        """.trimMargin()
                                        )
                                        println()
                                    }
                                    when (val option = validateInput("Int", "Select a dish: ")) {
                                        in 1..dishes.size -> {
                                            val dish = dishes.values.toList()[option as Int - 1]
                                            println()
                                            println("Dish selected: ${dish.name}")
                                            dish.quantity += 1
                                            user.addOrder(dish)
                                            println()
                                        }

                                        else -> {
                                            println()
                                            println("Wrong option")
                                            println()
                                        }
                                    }
                                }

                                2 -> {
                                    println()
                                    dishes = foodList.filter { it.value.category == Category.MAIN_COURSES }
                                    dishes.toList().forEachIndexed { index, entry ->
                                        val (key, value) = entry

                                        println(
                                            """${index + 1}. ${value.name}
                                            |Price: ${value.price}
                                            |Description: ${value.description}
                                        """.trimMargin()
                                        )
                                        println()
                                    }

                                    when (val option = validateInput("Int", "Select a dish: ")) {
                                        in 1..dishes.size -> {
                                            val dish = dishes.values.toList()[option as Int - 1]
                                            println()
                                            println("Dish selected: ${dish.name}")
                                            dish.quantity += 1
                                            user.addOrder(dish)
                                            println()
                                        }

                                        else -> {
                                            println()
                                            println("Wrong option")
                                            println()
                                        }
                                    }
                                }

                                3 -> {
                                    println()
                                    dishes = foodList.filter { it.value.category == Category.DRINKS }
                                    dishes.toList().forEachIndexed { index, entry ->
                                        val (key, value) = entry
                                        println(
                                            """${index + 1}. ${value.name}
                                            |Price: ${value.price}
                                            |Description: ${value.description}
                                        """.trimMargin()
                                        )
                                        println()
                                    }

                                    when (val option = validateInput("Int", "Select a drink: ")) {
                                        in 1..dishes.size -> {
                                            val dish = dishes.values.toList()[option as Int - 1]
                                            println()
                                            println("Drink selected: ${dish.name}")
                                            dish.quantity += 1
                                            user.addOrder(dish)
                                            println()
                                        }

                                        else -> {
                                            println()
                                            println("Wrong option")
                                            println()
                                        }
                                    }
                                }

                                4 -> {
                                    println()
                                    dishes = foodList.filter { it.value.category == Category.DESSERTS }
                                    dishes.toList().forEachIndexed { index, entry ->
                                        val (key, value) = entry
                                        println(
                                            """${index + 1}. ${value.name}
                                            |Price: ${value.price}
                                            |Description: ${value.description}
                                        """.trimMargin()
                                        )
                                        println()
                                    }

                                    when (val option = validateInput("Int", "Select a dessert: ")) {
                                        in 1..dishes.size -> {
                                            val dish = dishes.values.toList()[option as Int - 1]
                                            println()
                                            println("Dessert selected: ${dish.name}")
                                            dish.quantity += 1
                                            user.addOrder(dish)
                                            println()
                                        }

                                        else -> {
                                            println()
                                            println("Wrong option")
                                            println()
                                        }
                                    }
                                }

                                5 -> {

                                    runCatching {
                                        println()
                                        showOrder(user)
                                        if (totalBill(user) == 0.0) {
                                            throw Exception("You can't pay an empty order")
                                        }
                                        println()
                                        sleep(3_000L)
                                        var continuePay = validateInput(
                                            "String",
                                            "Do you want to pay your order? (Y/N): "
                                        )

                                        if (continuePay == "Y" || continuePay == "y") {
                                            sleep(2_000L)
                                            println()
                                            println("Which payment method do you want to use?")
                                            val listMethodPayment = MethodPayment.values().toList()
                                            listMethodPayment.forEachIndexed { index, methodPayment ->
                                                println("${index + 1}. ${methodPayment.method}")
                                            }

                                            payBill(
                                                user,
                                                listMethodPayment[validateInput(
                                                    "Int",
                                                    "Select a payment method: "
                                                ) as Int - 1]
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    println()
                                    println("Wrong option")
                                    println()
                                }
                            }
                        } while (!exit)

                    } else {
                        exit = false
                    }

                }

                2 -> {

                    do {
                        println()
                        println("You choose Sign up")
                        println()
                        print("User: ")
                        val userSignUp = readlnOrNull() ?: ""
                        print("Password: ")
                        val passwordSignUp = readlnOrNull() ?: ""

                        val userExist = userList.find { it.getUser() == userSignUp }
                        if (userExist != null) {
                            println("The user already exists")
                        } else {
                            user.signUp(userSignUp, passwordSignUp)
                            userList.add(user)
                        }
                    } while (userSignUp == "" && passwordSignUp == "")

                }

                3 -> {
                    println()
                    println("\t*************************")
                    println("\tThanks for using our app")
                    println("\t*************************")
                    exit = true
                }

                else -> {
                    println()
                    println("Wrong option")
                    println()
                }
            }
        } while (!exit)
    }

    override suspend fun showOrder(user: User) {
        println()
        println("Your order is being prepared")
        sleep(3_000L)
        println()
        if (user.getOrder().isEmpty()) {
            println("Your order is empty, please select a dish")
        } else {
            println("\t***********************************")
            println("\t\t\t\tWELCOME TO")
            println("\t\t\tFIRST CHOICE RESTAURANT")
            println("\t\t\t\t${LocalDate.now()}")
            println("\t\t\t\t${LocalTime.now().hour}:${LocalTime.now().minute}:${LocalTime.now().second}")
            println("\t\t\tUser - ${user.getUser()}")
            println()
            println("\t\tDishes: ")
            user.getOrder().forEach { dish ->
                println("\t\t\tName: ${dish.value.name}")
                println("\t\t\tPrice: ${dish.value.price}")
                println("\t\t\tQuantity: ${dish.value.quantity}")
                println()
            }
            println("\t***********************************")
            sleep(3_000L)
            println()
            println("Calculate of the total of your order: ")
            println("Total: ${totalBill(user)}")
        }


    }

    override fun totalBill(user: User): Double {
        var total = 0.0
        user.getOrder().forEach { dish ->
            total += dish.value.price * dish.value.quantity
        }
        return total
    }

    override fun payBill(user: User, listMethodPayment: MethodPayment) {

        val creditCard = CreditCard()

        when (listMethodPayment) {
            MethodPayment.CASH -> {
                println()
                sleep(3_000L)
                do {
                    val paymentCash = validateInput("Int", "Enter the amount of cash: ")
                    if (creditCard.paymentCash(paymentCash as Double, totalBill(user))) {
                        println()
                        println("Your payment is being processed")
                        sleep(3_000L)
                        println()
                        println("Thanks for your purchase")
                        println("Your change is: ${paymentCash.toString().toInt() - totalBill(user)}")
                        println()
                    } else {
                        println()
                        println("The amount of cash is not enough")
                        println()
                    }

                } while (paymentCash.toString().toInt() < totalBill(user))
                exit = true
            }

            MethodPayment.CREDIT_CARD -> {

                var passCorrect = false
                do {
                    println()
                    print("Enter your credit card number:")
                    val creditCardNumber = readlnOrNull() ?: ""
                    if (creditCard.creditCardPassword(creditCardNumber)) {
                        passCorrect = true
                    }
                } while (!passCorrect)

                println()
                println("Your payment is being processed")
                sleep(3_000L)
                creditCard.payment(totalBill(user))
                println()
                println("Thanks for your purchase")
                println()
                exit = true
            }

            MethodPayment.PAYPAL -> {
                var passCorrect = false
                do {
                    println()
                    print("Enter your credit card number:")
                    val creditCardNumber = readlnOrNull() ?: ""
                    if (creditCard.creditCardPassword(creditCardNumber)) {
                        passCorrect = true
                    }
                } while (!passCorrect)

                println()
                println("Your payment is being processed")
                sleep(3_000L)
                creditCard.payment(totalBill(user))
                println()
                println("Thanks for your purchase")
                println()
                exit = true
            }
        }
    }
}