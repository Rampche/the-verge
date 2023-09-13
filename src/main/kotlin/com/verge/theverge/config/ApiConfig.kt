package com.verge.theverge.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ApiConfig {

    @Bean
    fun usersMicroserviceOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("The Verge API")
                    .description("API to manipulate the backoffice of the restaurant.")
                    .version("1.0")
            )
    }
}