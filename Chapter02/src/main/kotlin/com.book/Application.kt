package com.book

import com.book.repository.MessageRepository
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.postgis.geojson.PostGISModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

/**
 * To launch Spring Boot application and hold the application level properties
 */
@SpringBootApplication
@EnableTransactionManagement
class Application {

    /**
     * Initialize our web app each time it runs
     */
    @Bean
    fun init(mr: MessageRepository) = CommandLineRunner {
        mr.createTable()
        mr.deleteAll()
    }

    /**
     * To deserialize-serialize the PostGIS data structures
     */
    @Bean
    fun objectMapper(): ObjectMapper =
            Jackson2ObjectMapperBuilder()
                    .modulesToInstall(PostGISModule())
                    .serializationInclusion(JsonInclude.Include.NON_NULL)
                    .build()

    /**
     * Configuring transaction support
     */
    @Bean
    fun transactionManager(@Qualifier("dataSource") dataSource: DataSource) = SpringTransactionManager(dataSource)
}

/**
 * Launch the Spring Boot application
 */
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
