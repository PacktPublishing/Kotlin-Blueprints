package com.book.web

import com.book.domain.Message
import com.book.repository.MessageRepository
import org.postgis.PGbox2d
import org.postgis.Point
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

/**
 * Exposes the operations related to creating and showing
 * messages through URLs using REST
 */
@RestController
@RequestMapping("/message")
class MessageController(val repository: MessageRepository) {

    val broadcaster = ReactiveBroadcaster()

    /**
     * Creates new message and saves it into DB
     */
    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody message: Message): Message {
        val msg = repository.insert(message)
        broadcaster.send(msg)
        return msg
    }

    /**
     * Get list of all the messages
     */
    @GetMapping
    fun list() = repository.findAll()

    /**
     * Get list of messages in the given bounds
     */
    @GetMapping("/bbox/{xMin},{yMin},{xMax},{yMax}")
    fun findByBoundingBox(@PathVariable xMin: Double, @PathVariable yMin: Double,
                          @PathVariable xMax: Double, @PathVariable yMax: Double)
            = repository.findByBoundingBox(PGbox2d(Point(xMin, yMin), Point(xMax, yMax)))

    /**
     * Subscribes to receive the updates regarding the messages
     */
    @GetMapping("/subscribe")
    fun subscribe() = broadcaster.subscribe()
}