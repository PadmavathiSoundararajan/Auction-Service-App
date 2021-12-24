package com.auction.bidding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@ComponentScan(basePackages = "com.auction.bidding")
@EnableFeignClients
@EnableEurekaClient
public class QueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryServiceApplication.class, args);
	}

}
