package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.valueobjects.Money;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        final var expectedTotal = Money.of("44.00");

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
        assertEquals(expectedSaleItems, actualSale.getItems());
        assertEquals(expectedStatus, actualSale.getStatus());
        assertEquals(expectedTotal, actualSale.getTotal());

    }

    @Test
    void givenAInvalidQtyItems_whenCreateASale_thenThrowException() {

        // Given
        final var expectedMessage = "Sale must have at least one item";

        // When
        final var actualException =
                assertThrows(RuntimeException.class,
                        () -> Sale.of(
                                "123",
                                Salesman.of(
                                        "John",
                                        "123.456.789-00",
                                        "jonh@gmail.com",
                                        "(88) 99797 9990"
                                ),
                                List.of()
                        )
                );


        // Then
        assertEquals(expectedMessage, actualException.getMessage());
    }


}
