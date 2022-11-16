package br.com.pottencial.domain;

import br.com.pottencial.domain.entity.Sale;
import br.com.pottencial.domain.entity.SaleItem;
import br.com.pottencial.domain.entity.Salesman;
import br.com.pottencial.domain.valueobjects.Money;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Given
        final var expectedOrderId = "123";

        final var expectedSalesman = Salesman.of(
                "John",
                "123.456.789-00",
                "jonh@gmail.com",
                "(88) 99797 9990"
        );

        final var expectedSaleItems = List.of(
                SaleItem.of("Coca-Cola", Money.of("8.00"), 2),
                SaleItem.of("Pepsi", Money.of("7.00"), 4)
        );

        final var expectedStatus = Sale.Status.WAITING_PAYMENT;
        final var expectedTotal = Money.of("44.00");

        // When
        final var actualSale = Sale.of(
                expectedOrderId,
                expectedSalesman,
                expectedSaleItems
        );

        actualSale.update(Sale.Status.DELIVERED);

        // Then
        System.out.println(actualSale.getStatus());

    }
}
