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
        WAITING_PAYMENT,
    }
}
