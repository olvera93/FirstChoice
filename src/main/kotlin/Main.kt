import entity.User
import utils.Menu

suspend fun main() {

    val user = User()
    val userList = mutableListOf<User>()
    val menu = Menu(
    )
    menu.showMenu(user, userList)

}