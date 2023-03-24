package interfaces

import entity.User

interface ShowOrder {

    suspend fun showOrder(user: User)
}