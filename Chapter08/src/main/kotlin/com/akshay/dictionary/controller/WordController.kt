package com.akshay.dictionary.controller

import com.akshay.dictionary.model.Meaning
import tornadofx.*

/**
 * Abstraction layer to perform HTTP operations
 */
class WordController : Controller() {

    private val api: Rest by inject()

    /**
     * Sends a GET request to fetch the meaning
     * from the API endpoint
     * @param word to get the meaning for
     * @return meaning object if success else null
     */
    fun getMeaning(word: String): Meaning? {
        val response = api.get("$word/definitions")
        try {
            return when {
                response.ok() -> response.one().toModel()
                else -> null
            }
        } finally {
            response.consume()
        }
    }
}