package entity

import java.util.UUID

data class FoodMenu(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val price: Double,
    val description: String,
    var quantity: Int = 0,
    val category: Category
)

val foodList = mutableMapOf(

    UUID.randomUUID().toString() to FoodMenu(
        name = "Salad",
        price = 7.0,
        description = "Salad with chicken",
        category = Category.STARTERS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Soup",
        price = 5.0,
        description = "Soup of the day",
        category = Category.STARTERS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Rice",
        price = 8.0,
        description = "Rice with chicken",
        category = Category.STARTERS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Noodles",
        price = 6.0,
        description = "Noodles with chicken",
        category = Category.STARTERS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Pasta",
        price = 9.0,
        description = "Pasta with tomato sauce",
        category = Category.MAIN_COURSES
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Pizza",
        price = 10.0,
        description = "Pizza with cheese",
        category = Category.MAIN_COURSES
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Hamburger",
        price = 8.0,
        description = "Hamburger with cheese",
        category = Category.MAIN_COURSES
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Hot dog",
        price = 7.6,
        description = "Hot dog with cheese",
        category = Category.MAIN_COURSES
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Ice cream",
        price = 3.0,
        description = "Ice cream with chocolate",
        category = Category.DESSERTS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Cheesecake",
        price = 6.5,
        description = "Cheesecake with strawberry",
        category = Category.DESSERTS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Cupcake",
        price = 4.0,
        description = "Cupcake with chocolate",
        category = Category.DESSERTS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Brownie",
        price = 5.0,
        description = "Brownie with chocolate",
        category = Category.DESSERTS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Coca cola",
        price = 2.5,
        description = "Coca cola",
        category = Category.DRINKS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Fanta",
        price = 2.0,
        description = "Fanta",
        category = Category.DRINKS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Sprite",
        price = 2.0,
        description = "Sprite",
        category = Category.DRINKS
    ),

    UUID.randomUUID().toString() to FoodMenu(
        name = "Water",
        price = 1.5,
        description = "Water",
        category = Category.DRINKS
    )

)
