package com.amadeus.flightsearch.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("FLIGHT SEARCH API")
                        .description("Flight Search Api")
                        .version("1.0"));
    }

    @Bean
    public GroupedOpenApi airportApi() {
        return GroupedOpenApi.builder()
                .group("Airport API")
                .pathsToMatch("/airport/**")
                .build();
    }

    @Bean
    public GroupedOpenApi expenseApi() {
        return GroupedOpenApi.builder()
                .group("Flight API")
                .pathsToMatch("/flight/**")
                .build();
    }

}
