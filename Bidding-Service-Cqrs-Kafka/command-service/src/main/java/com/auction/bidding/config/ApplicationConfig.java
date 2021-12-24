package com.auction.bidding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@EnableMongoRepositories(basePackages = "com.auction.api.biddingservice.repository")
@Configuration
public class ApplicationConfig {

    /*@Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }*/

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /*@Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        Seller seller = new Seller(new Seller.SellerBuilder().setFirstName("Seller1").setLastName("sell").setAddress(",Vasant vihar").setCity("Mumbai").setEmail("abc@gmail.com").setPhone("123456789").setPin("400060").setState("Maharashtra"));

        return strings -> {
            productRepository.save(new Product(new Product.ProductBuilder("Product1").shortDescription("P1").detailedDescription("First product").category(ProductCategoryEnum.ORNAMENT).startingPrice(100L).bidEndDate(new Date()).seller(seller)));
            productRepository.save(new Product(new Product.ProductBuilder("Product2").shortDescription("P2").detailedDescription("Second product").category(ProductCategoryEnum.SCULPTOR).startingPrice(200L).bidEndDate(new Date()).seller(seller)));
        };
    }*/
}