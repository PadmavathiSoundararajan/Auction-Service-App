package com.auction.api.productbiddingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.auction.api.productbiddingservice")
@EnableFeignClients
@EnableEurekaClient
public class ProductBiddingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductBiddingServiceApplication.class, args);
	}

}
