package com.news.routes

import com.github.kittinunf.fuel.httpGet
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get

/**
 * Get list of all news sources
 */
fun Route.newsSources() {
    get("/news-source") {
        val (_, _, result) = "https://newsapi.org/v1/sources".httpGet().responseString()
        call.respondText(result.get(), ContentType.Application.Json)
    }
}

/**
 * Get news from a particular source
 */
fun Route.newsArticles() {
    get("/news/{source}") {
        val source = call.parameters["source"]
        val (_, _, result) = "https://newsapi.org/v1/articles?source=$source".httpGet()
                .header("x-api-key" to "31dd9773e8d3482b949b9ae6a098c7fe")
                .responseString()
        call.respondText(result.get(), ContentType.Application.Json)
    }
}