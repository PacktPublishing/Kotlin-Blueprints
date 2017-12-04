package com.akshay.dictionary.view

import com.akshay.dictionary.Constants
import com.akshay.dictionary.app.Styles.Companion.cssRule
import com.akshay.dictionary.controller.WordController
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import tornadofx.*

/**
 * Base view shown when the application is started
 */
class MainView : View("Dictionary") {

    // Get the REST client
    private val api: Rest by inject()
    private val controller: WordController by inject()

    // UI elements
    private var inputWord: TextField by singleAssign()
    private var result: Label by singleAssign()

    init {
        // Configuring the client
        api.baseURI = "https://wordsapiv1.p.mashape.com/words/"
        api.engine.requestInterceptor = {
            (it as HttpURLRequest).addHeader("X-Mashape-Key", Constants.WORDS_API_KEY)
        }
    }

    /**
     * Root View
     */
    override val root = vbox {
        addClass(cssRule)
        form {
            fieldset(labelPosition = Orientation.VERTICAL) {
                field("Enter word", Orientation.VERTICAL) {
                    inputWord = textfield()
                }
                buttonbar {
                    button("Get meaning") {
                        action {
                            runAsync {
                                // Send request to API
                                controller.getMeaning(inputWord.text)
                            } ui { meaning ->
                                if (meaning != null) {
                                    // Show the result on the UI
                                    val meanings = meaning.definitions.joinToString(
                                            "\n • ",
                                            "\n • ") { it.definition }
                                    result.text = "Meaning(s): $meanings"
                                } else {
                                    result.text = "Unable to find the meaning"
                                }
                            }
                        }
                    }
                }
            }
            // Result
            result = label { }
        }
    }
}