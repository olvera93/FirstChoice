package utils

import entity.Category
import entity.FoodMenu
import entity.User
import entity.abstracts.FullPayment
import entity.foodList
import interfaces.ShowOrder
import java.lang.Thread.sleep

class Menu : FullPayment(), ShowOrder {

    private var exit = false
    private lateinit var dishes: Map<String, FoodMenu>

    suspend fun showMenu(user: User, userList: MutableList<User>) {
        do {
            println()
            println("1. Sign in")
            println("2. Sign up")
            println("3. Exit")

            when (validateInput("Int", "Select an option: ")) {
                1 -> {

                    println()
                    println("Sign in")
                    println()
                    print("User: ")
                    val userSignIn = readlnOrNull() ?: ""

                    print("Password: ")
                    val passwordSignIn = readlnOrNull() ?: ""

                    if (user.signIn(userSignIn, passwordSignIn)) {

                        do {
                            println()
                            val listOfCategory = Category.values().toList()
                            println("Menu options")
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
                                    }.onFailure {
                                        println("Your order is empty")
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
                        println("Sign up")
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
            user.getOrder().forEach { dish ->
                println(
                    """${dish.value.name}
                |Price: ${dish.value.price}
                |Quantity: ${dish.value.quantity}
            """.trimMargin()
                )
                println()
            }
            sleep(3_000L)
            println()
            println("Calculate of the total of your order: ")
            println("Total: ${fullPayment(user)}")
        }


    }

    override fun fullPayment(user: User): Double {
        var total = 0.0
        user.getOrder().forEach { dish ->
            total += dish.value.price * dish.value.quantity
        }
        return total

    }

}