package com.auction.bidding.aggregates;

import com.auction.bidding.commands.AddBidCommand;
import com.auction.bidding.commands.UpdateBidAmountCommand;
import com.auction.bidding.events.BidAddedEvent;
import com.auction.bidding.events.UpdateBidAmountEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class BidAggregate {

    private static final Logger LOG = LoggerFactory.getLogger(BidAggregate.class);

    /**
     * Aggregates that are managed by Axon must have a unique identifier.
     * Strategies similar to GUID are often used. The annotation 'AggregateIdentifier'
     * identifies the id field as such.
     */
    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String phone;
    private String email;
    private String productId;
    private Long bidAmount;

    /**
     * This default constructor is used by the Repository to construct
     * a prototype BidAggregate. Events are then used to set properties
     * such as the BidAggregate's Id in order to make the Aggregate reflect
     * it's true logical state.
     */
    public BidAggregate() {
    }

    /**
     * This constructor is marked as a 'CommandHandler' for the
     * AddBidCommand. This command can be used to construct
     * new instances of the Aggregate. If successful a new BidAddedEvent
     * is 'applied' to the aggregate using the Axon 'apply' method. The apply
     * method appears to also propagate the Event to any other registered
     * 'Event Listeners', who may take further action.
     *
     * @param command
     */
    @CommandHandler
    public BidAggregate(AddBidCommand command) {
        LOG.info("Command: 'AddBidCommand' received.");
        apply(new BidAddedEvent(command.getId(), command.getFirstName(), command.getFirstName(), command.getAddress(), command.getCity(), command.getState(), command.getPin(), command.getPhone(), command.getEmail(), command.getProductId(), command.getBidAmount()));
    }

    @CommandHandler
    public BidAggregate(UpdateBidAmountCommand command) {
        LOG.info("Command: 'Update Bid Command' received.");
        apply(new UpdateBidAmountEvent(command.getId(), command.getEmail(), command.getProductId(), command.getBidAmount()));
    }

    /**
     * This method is marked as an EventSourcingHandler and is therefore used by the Axon framework to
     * handle events of the specified type (BidAddedEvent). The BidAddedEvent can be
     * raised either by the constructor during BidAggregate(AddPBidCommand) or by the
     * Repository when 're-loading' the aggregate.
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(com.auction.bidding.events.BidAddedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.address = event.getAddress();
        this.city = event.getCity();
        this.state = event.getState();
        this.phone = event.getPhone();
        this.pin = event.getPin();
        this.email = event.getEmail();
        this.productId = event.getProductId();
        this.bidAmount = event.getBidAmount();
        LOG.debug("Applied: 'BidAddedEvent' [{}] '{}'", event.getId());
    }

    @EventSourcingHandler
    public void on(UpdateBidAmountEvent event) {
        this.bidAmount = event.getBidAmount();
        LOG.debug("Applied: 'Bid update Event' [{}] '{}'", event.getId());
    }


}
