package com.plb.vinylmgt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .host("http://localhost:8080")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.plb.vinylmgt.web.rest"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My Vinyl API",
                "Manage my vinyl",
                "1.0.0",
                "Term of service",
                new Contact("John Doe", "www.example.com", "myaddres@company.com"),
                "license of API", "API license URL", Collections.emptyList()
        );
    }
}
