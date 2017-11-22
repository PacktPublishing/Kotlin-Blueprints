package com.book.web

import org.springframework.http.MediaType
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException
import java.util.*
import java.util.Collections.synchronizedSet

/**
 * Handles the event broadcasting to the observers in an
 * asynchronous way.
 */
class ReactiveBroadcaster {

    /**
     * Set of emitters for multiple events
     */
    private var emitters = synchronizedSet(HashSet<SseEmitter>())

    /**
     * Subscribe to the event
     */
    fun subscribe(): SseEmitter {
        val sseEmitter = SseEmitter()
        // Stop observing the event on completion
        sseEmitter.onCompletion({ this.emitters.remove(sseEmitter) })
        this.emitters.add(sseEmitter)
        return sseEmitter
    }

    /**
     * Trigger the event update to the observers
     */
    fun send(o: Any) {
        synchronized(emitters) {
            emitters.iterator().forEach {
                try {
                    it.send(o, MediaType.APPLICATION_JSON)
                } catch (e: IOException) {
                }
            }
        }
    }
}