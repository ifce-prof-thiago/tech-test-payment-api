package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.Auditable;
import br.com.pottencial.domain.valueobjects.Money;

import java.util.UUID;

public class SaleItem extends BaseEntity {

    private String name;
    private Money price;
    private int quantity;


    public SaleItem(final UUID id, final String name, final Money price, final int quantity, final Auditable auditable) {
        super(id, auditable);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static SaleItem of(final String name, final Money price, final int quantity) {
        final var id = UUID.randomUUID();
        final var auditable = Auditable.of();
        return new SaleItem(id, name, price, quantity, auditable);
    }

    @Override
    public void validate() {

    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getTotal() {
        return price.multiply(quantity);
    }
}
