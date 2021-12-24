package com.auction.bidding.config;

import com.auction.bidding.aggregates.BidAggregate;
import com.mongodb.MongoClient;
import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AxonConfig {

    @Autowired
    private EventStore myEventStore;

    @Bean
    public CommandBus commandBus(TransactionManager transactionManager) {
        SimpleCommandBus commandBus = new SimpleCommandBus(transactionManager, NoOpMessageMonitor.INSTANCE);
        commandBus.registerDispatchInterceptor(new BeanValidationInterceptor());
        return commandBus;
    }

    @Bean
    public MongoEventStorageEngine eventStore(MongoClient client) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(client));
    }

    @Bean
    public AggregateFactory<BidAggregate> aggregateFactory() {
        return new GenericAggregateFactory<>(BidAggregate.class);
    }

    @Bean
    public Snapshotter snapShotter(AggregateFactory<BidAggregate> aggregateFactory) {
        return new AggregateSnapshotter(myEventStore, aggregateFactory);
    }


    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

    @Bean
    public Repository<BidAggregate> accountAggregateRepository(SnapshotTriggerDefinition snapshotTriggerDefinition, AggregateFactory<BidAggregate> aggregateFactory) {
        return new EventSourcingRepository<BidAggregate>(aggregateFactory, myEventStore, snapshotTriggerDefinition);
    }
}
