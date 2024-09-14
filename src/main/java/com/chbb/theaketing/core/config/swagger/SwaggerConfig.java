package com.chbb.theaketing.core.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
    GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("사용자 Api")
                .pathsToMatch(
                        "/api/u/*/login",
                        "/api/u/*/join",
                        "/api/u/**")
                .build();
    }

    @Bean
    GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("전체 Api")
                .pathsToMatch(
                        "/**")
                .build();
    }

    @Bean
    OpenAPI openApi() {
        Info info = new Info()
                .title("티케팅")
                .version("1.0.0")
                .description("티케팅 api");
        SecurityScheme auth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name("JSESSIONID");
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("basicAuth");

        return new OpenAPI()
                .info(info)
                .components(new Components()
                        .addSecuritySchemes("basicAuth", auth))
                .addSecurityItem(securityRequirement);
    }

}
