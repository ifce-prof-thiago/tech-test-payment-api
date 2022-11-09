package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.valueobjects.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleItemTest {

    @Test
    void givenAValidParams_whenCreateASaleItem_thenReturnASaleItemCreated() {

        // Given
        final var expectedQuantity = 10;
        final var expectedPrice = Money.of("10.0");
        final var expectedName = "Product 1";
        final var expectedTotal = Money.of("100.0");

        // When
        final var actualSaleItem = SaleItem.of(
                expectedName,
                expectedPrice,
                expectedQuantity
        );

        // Then
        assertNotNull(actualSaleItem);
        assertNotNull(actualSaleItem.getId());
        assertEquals(expectedQuantity, actualSaleItem.getQuantity());
        assertEquals(expectedPrice, actualSaleItem.getPrice());
        assertEquals(expectedName, actualSaleItem.getName());
        assertEquals(expectedTotal, actualSaleItem.getTotal());

    }


}