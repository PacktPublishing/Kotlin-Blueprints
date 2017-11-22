package com.book.domain

import org.postgis.Point

/**
 * It represents the message shown on the maps
 */
data class Message(
        // The message
        var content: String,
        // Location of the message
        var location: Point? = null,
        var id: Int? = null
)

