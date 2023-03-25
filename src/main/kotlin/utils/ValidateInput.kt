package utils

import java.lang.Exception

fun validateInput(expected: String, inputMessage: String): Any {
    do {
        print(inputMessage)
        val value = readLine() ?: ""
        var typeInput = ""

        try {
            when(expected) {
                "String" -> {
                    typeInput = "String"
                    return value
                }

                "Int" -> {
                    typeInput = "Int"
                    return value.toInt()
                }

                "Float" -> {
                    typeInput = "Float"
                    return value.toFloat()
                }

                "Boolean" -> {
                    typeInput = "Boolean"
                    return value.toBoolean()
                }

                else -> {
                    println("Wrong of type")
                }
            }
        } catch (e: Exception) {
            println("I hope an valid input $typeInput")
            println()
        }
    } while (true)
}