package views

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onInputFunction
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.js.Json

class ChatWindow(val callback: (String) -> Unit) {

    private var chatMessage: String? = null
    private lateinit var nickName: String

    fun showChatWindow(nickName: String) {
        this.nickName = nickName
        val formContainer = document.getElementById("container") as HTMLDivElement
        val chatWindow = document.create.div(classes = "chatWindow") {
            h2(classes = "chatWelcomeMessage") {
                +"Welcome to Kotlin Blueprints chat app"
            }
            hr { }
            div {
                id = "leftDiv"
                ul(classes = "chatMessages") {
                    id = "chatMessages"
                }
                div {
                    input(classes = "chatMessageInput") {
                        id = "chatInputBox"
                        placeholder = "Enter your message here..."
                        maxLength = 100.toString()
                        onInputFunction = onMessageInput()
                    }
                    button(classes = "sendMessageButton") {
                        +"Send Message"
                        type = ButtonType.button
                        onClickFunction = onSendMessageClicked()
                    }
                }
            }
            div {
                id = "rightDiv"
                h4 {
                    +"Online users"
                }
                hr { }
                ul(classes = "onlineUserList") {
                    id = "onlineUsersList"
                }
            }
            div(classes = "clearBoth")
        }
        formContainer.appendChild(chatWindow)
    }

    fun showOnlineUsers(data: Json) {
        val onlineUsersList = document.getElementById("onlineUsersList")
        onlineUsersList?.let {
            val usersList = data["usersList"] as? Array<String>
            usersList?.forEachIndexed { _, nickName ->
                it.appendChild(getUserListItem(nickName))
            }
        }
    }

    fun doSomething(data: String?) {
        if (data != null) {
            println(data.length)
            println(data.compareTo("Kotlin Blueprints"))
            println(data.toUpperCase())
            println(data.first())
            println(data.last())
        }
    }

    private fun getUserListItem(nickName: String): HTMLElement {
        return document.create.li(classes = "onlineUserListItem") {
            span(classes = "ringInitials") {
                +getInitials(nickName)
            }
            span(classes = "onlineUserListItemText") {
                +nickName
            }
        }
    }

    fun addNewUsers(data: Json) {
        val onlineUsersList = document.getElementById("onlineUsersList")
        onlineUsersList?.appendChild(getUserListItem(data["nickName"].toString()))
    }

    fun showNewUserJoined(data: Json) {
        logListItem("${data["nickName"]} has joined")
        val noOfUsers = data["numOfUsers"] as Int
        logListItem(getParticipantsMessage(noOfUsers))
    }

    private fun getParticipantsMessage(noOfUsers: Int) =
            if (noOfUsers == 1) "There is $noOfUsers participant" else "There are $noOfUsers participants"


    private fun logListItem(message: String) {
        val onlineUsersList = document.getElementById("chatMessages")
        val li = document.create.li(classes = "log") {
            p {
                +message
            }
        }
        onlineUsersList?.appendChild(li)
    }

    fun showUserLeft(data: Json) {
        logListItem("${data["nickName"]} left")
        logListItem(getParticipantsMessage(data["numOfUsers"] as Int))
    }

    fun removeUserFromList(data: Json) {
        val onlineUsersList = document.getElementById("onlineUsersList")
        val childNodes = onlineUsersList?.childNodes

        for (child in 0..onlineUsersList?.childElementCount!!) {
            val element = childNodes!![child]

        }

        /*val onlineUsersList = document.getElementById(data["nickName"] as String)
        onlineUsersList?.remove()*/
    }

    private fun onMessageInput(): (Event) -> Unit {
        return {
            val input = it.currentTarget as HTMLInputElement
            chatMessage = input.value
        }
    }

    private fun onSendMessageClicked(): (Event) -> Unit {
        return {
            if (chatMessage?.isNotBlank() as Boolean) {
                val formContainer = document.getElementById("chatInputBox") as HTMLInputElement
                callback(chatMessage!!)
                logMessageFromMe(nickName = nickName, message = chatMessage!!)
                formContainer.value = ""
            }
        }
    }

    fun showNewMessage(data: Json) {
        logMessage(message = data["message"] as String, nickName = data["nickName"] as String)
    }

    private fun logMessage(nickName: String, message: String) {
        val onlineUsersList = document.getElementById("chatMessages")
        val li = document.create.li {
            div(classes = "receivedMessages") {
                span(classes = "filledInitials") {
                    +getInitials(nickName)
                }
                span(classes = "chatMessage") {
                    +message
                }
            }
        }
        onlineUsersList?.appendChild(li)
    }

    private fun logMessageFromMe(nickName: String, message: String) {
        val onlineUsersList = document.getElementById("chatMessages")
        val li = document.create.li {
            div(classes = "sentMessages") {
                span(classes = "chatMessage") {
                    +message
                }
                span(classes = "filledInitialsMe") {
                    +getInitials(nickName)
                }
            }
        }
        onlineUsersList?.appendChild(li)
    }

    private fun getInitials(nickName: String): String {
        val splitNames = nickName.split(" ")
        var message = ""
        if (splitNames.size > 1) {
            message += splitNames[0].first().toString().toUpperCase()
            message += splitNames[1].first().toString().toUpperCase()
        } else {
            message += if (nickName.length > 2) nickName.substring(0, 2).toUpperCase() else nickName.toUpperCase()
        }

        return message
    }
}