package com.akshay.dictionary.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class Definition : JsonModel {

    val definitionProperty = SimpleStringProperty()
    var definition by definitionProperty

    val partOfSpeechProperty = SimpleStringProperty()
    var partOfSpeech by partOfSpeechProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            definition = string("definition")
            partOfSpeech = string("partOfSpeech")
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("definition", definition)
            add("partOfSpeech", partOfSpeech)
        }
    }

    override fun toString(): String {
        return definition
    }

}