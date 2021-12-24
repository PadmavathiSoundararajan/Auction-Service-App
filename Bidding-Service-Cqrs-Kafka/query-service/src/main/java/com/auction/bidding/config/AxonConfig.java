package com.auction.bidding.config;

import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AxonConfig {

    @ConditionalOnMissingBean
    @Bean
    public KafkaMessageConverter<String, byte[]> kafkaMessageConverter(
            @Qualifier("eventSerializer") Serializer eventSerializer) {
        return new DefaultKafkaMessageConverter(eventSerializer);
    }


    @Bean
    public CommandBus commandBus(TransactionManager transactionManager) {
        SimpleCommandBus commandBus = new SimpleCommandBus(transactionManager, NoOpMessageMonitor.INSTANCE);
        //SimpleCommandBus commandBus = SimpleCommandBus.builder().transactionManager(transactionManager).messageMonitor(NoOpMessageMonitor.INSTANCE).build();
        commandBus.registerDispatchInterceptor(new BeanValidationInterceptor());
        return commandBus;
    }

}
