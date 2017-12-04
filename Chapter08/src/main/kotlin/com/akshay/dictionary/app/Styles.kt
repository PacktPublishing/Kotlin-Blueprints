package com.akshay.dictionary.app

import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val cssRule by cssclass()
    }

    init {
        select(cssRule) {
            minHeight = 200.px
            minWidth = 300.px
        }
    }
}