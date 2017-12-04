import views.ChatWindow
import views.LoginWindow


fun main(args: Array<String>) {

    val socket: dynamic = js("window.socket")

    val chatWindow = ChatWindow {
        socket.emit("new_message", it)
    }

    val loginWindow = LoginWindow {
        chatWindow.showChatWindow(it)
        socket.emit("add_user", it)
    }
    loginWindow.showLogin()


    socket.on("login", { data ->
        chatWindow.showNewUserJoined(data)
        chatWindow.showOnlineUsers(data)
    })

    socket.on("user_joined", { data ->
        chatWindow.showNewUserJoined(data)
        chatWindow.addNewUsers(data)
    })

    socket.on("user_left", { data ->
        chatWindow.showUserLeft(data)
    })

    socket.on("new_message", { data ->
        chatWindow.showNewMessage(data)
    })
}