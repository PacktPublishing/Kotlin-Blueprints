package views

import kotlinx.html.button
import kotlinx.html.dom.create
import kotlinx.html.h3
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onInputFunction
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.browser.document

class LoginWindow(val callback: (String) -> Unit) {

    private lateinit var nickName: String
    private lateinit var email: String

    fun showLogin() {
        val formContainer = document.getElementById("container") as HTMLDivElement
        val loginDiv = document.create.div {
            id = "loginDiv"
            h3(classes = "title") {
                +"Welcome to Kotlin Blueprints chat app"
            }
            input(classes = "nickNameInput") {
                id = "nickName"
                onInputFunction = onInput()
                maxLength = 16.toString()
                placeholder = "Enter your nick name"
            }
            button(classes = "loginButton") {
                +"Login"
                onClickFunction = onLoginButtonClicked()
            }
        }
        formContainer.appendChild(loginDiv)
    }

    private fun onLoginButtonClicked(): (Event) -> Unit {
        return {
            if (!nickName.isBlank()) {
                val formContainer = document.getElementById("loginDiv") as? HTMLDivElement
                formContainer?.remove()
                callback(nickName)
            }
        }
    }


    private fun onInput(): (Event) -> Unit {
        return {
            val input = it.currentTarget as HTMLInputElement
            when (input.id) {
                "nickName" -> nickName = input.value
                "emailId" -> email = input.value
            }
        }
    }
}