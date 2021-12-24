package com.auction.bidding.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Product-Service",version = "1.0",description = "Product Microservice"))
public class SwaggerConfig {

   /* @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }*/
}
