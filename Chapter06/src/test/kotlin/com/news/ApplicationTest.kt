package com.news

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun `check index page`() = withTestApplication(Application::main) {
        with(handleRequest(HttpMethod.Get, "/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello readers!", response.content)
        }
    }
}