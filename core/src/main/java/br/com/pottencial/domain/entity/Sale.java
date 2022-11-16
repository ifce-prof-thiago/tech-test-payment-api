package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.Auditable;
import br.com.pottencial.domain.valueobjects.Money;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Sale extends AggregateRoot {

    private String orderId;
    private Salesman salesman;
    private List<SaleItem> items;
    private Status status;

    private Sale(
            final UUID id,
            final String orderId,
            final Salesman salesman,
            final List<SaleItem> items,
            final Status status,
            final boolean enabled,
            final Auditable auditable) {

        super(id, enabled, auditable);
        this.orderId = orderId;
        this.salesman = salesman;
        this.items = items;
        this.status = status;

        this.validate();

    }

    @Override
    public void validate() {

        if (items == null || items.isEmpty()) {
            throw new RuntimeException("Sale must have at least one item");
        }

    }

    public static Sale of(
            final String orderId,
            final Salesman salesman,
            final List<SaleItem> items) {

        final var id = UUID.randomUUID();
        final var auditable = Auditable.of();

        return new Sale(
                id,
                orderId,
                salesman,
                items,
                Status.WAITING_PAYMENT,
                true,
                auditable);
    }

    public void update(final Status status) {
        switch (status) {
            case APPROVED -> this.approve();
            case DELIVERED -> this.deliver();
            case CANCELED -> this.cancel();
            case DELIVERED_TO_CARRIER -> this.deliverToCarrier();
        }

        this.update();
    }

    private void approve() {

        if (this.status != Status.WAITING_PAYMENT) {
            throw new RuntimeException("Sale must be in WAITING_PAYMENT status");
        }

        this.status = Status.APPROVED;
    }

    private void cancel() {

        if (this.status != Status.WAITING_PAYMENT && this.status != Status.APPROVED) {
            throw new RuntimeException("Sale must be in WAITING_PAYMENT or APPROVED status");
        }

        this.status = Status.CANCELED;
    }

    private void deliverToCarrier() {

        if (this.status != Status.APPROVED) {
            throw new RuntimeException("Sale must be in APPROVED status");
        }

        this.status = Status.DELIVERED_TO_CARRIER;
    }

    private void deliver() {

        if (this.status != Status.DELIVERED_TO_CARRIER) {
            throw new RuntimeException("Sale must be in DELIVERED_TO_CARRIER status");
        }

        this.status = Status.DELIVERED;
    }


    public Salesman getSalesman() {
        return salesman;
    }

    public List<SaleItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getOrderId() {
        return orderId;
    }

    public Status getStatus() {
        return status;
    }

    public Money getTotal() {
        return items.stream()
                .map(SaleItem::getTotal)
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }

    public enum Status {
        WAITING_PAYMENT, APPROVED, CANCELED, DELIVERED_TO_CARRIER, DELIVERED
    }
}
