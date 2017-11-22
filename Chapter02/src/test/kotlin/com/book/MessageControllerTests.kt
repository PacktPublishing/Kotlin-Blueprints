package com.book

import com.book.domain.Message
import com.book.repository.MessageRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.postgis.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest
class MessageControllerTests {

    @Autowired lateinit var context: WebApplicationContext
    @Autowired lateinit var messageRepository: MessageRepository
    @Autowired lateinit var mapper: ObjectMapper

    /**
     * Entry point to server side tests
     */
    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        messageRepository.deleteAll()
        mockMvc = webAppContextSetup(this.context).build()
    }

    @Test
    fun `Create new message`() {
        val message = Message("""We have some dummy message over here to
                                perform some testing and I have started to
                                write test cases for my code""".trimMargin()
                , Point(0.0, 0.0))
        mockMvc.perform(post("/message")
                .content(mapper.writeValueAsString(message))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated)
    }

    @Test
    fun `Get all messages`() {
        messageRepository.insert(Message("I love Kotlin"))
        messageRepository.insert(Message("I love this book", Point(0.0, 0.0)))
        mockMvc.perform(get("/message").accept(APPLICATION_JSON_UTF8)).andExpect(status().isOk)
    }

    @Test
    fun `Find messages in the specified region`() {
        messageRepository.insert(Message("I love Kotlin", Point(0.0, 0.0)))
        messageRepository.insert(Message("I love this book", Point(1.0, 1.0)))
        mockMvc.perform(get("/message/bbox/{xMin},{yMin},{xMax},{yMax}", -1, -1, 2, 2)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
    }

    @Test
    fun `Subscribe to the message`() {
        mockMvc.perform(get("/message/subscribe")).andExpect(status().isOk)
    }

}