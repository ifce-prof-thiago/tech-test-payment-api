package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.valueobjects.Money;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SaleTest {

    @Test
    void givenAValidParams_whenCreateASale_thenReturnASaleCreated() {

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
        final var expectedTotal = Money.of("46.00");

        // When
        final var actualSale = Sale.of(
                expectedOrderId,
                expectedSalesman,
                expectedSaleItems
        );

        // Then
        assertNotNull(actualSale);
        assertNotNull(actualSale.getId());
        assertEquals(expectedOrderId, actualSale.getOrderId());
        assertEquals(expectedSalesman, actualSale.getSalesman());
        assertEquals(expectedSaleItems, actualSale.getSaleItems());
        assertEquals(expectedStatus, actualSale.getStatus());
        assertEquals(expectedTotal, actualSale.getTotal());

    }
}
