package com.news

import com.news.routes.newsArticles
import com.news.routes.newsSources
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * Starting point
 */
fun Application.main() {
    install(DefaultHeaders)
    // Adds logging
    install(CallLogging)
    // Adds URL routes
    install(Routing) {
        get("/") {
            call.respondText("Hello readers!", ContentType.Text.Html)
        }
        newsSources()
        newsArticles()
    }
}