package com.respapi.udemykotlinrestapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springdoc.core.GroupedOpenApi


@Configuration
class SwaggerConfig {

//
//    @Value("/v3/api-doc")
    private  var apiDocsPath: String = "/v3/api-doc"

    @Bean
    fun groupedOpenApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("api")
            .pathsToMatch("/v1/**")
            .build()
    }
}