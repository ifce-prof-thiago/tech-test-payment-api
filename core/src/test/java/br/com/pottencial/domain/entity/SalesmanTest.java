package br.com.pottencial.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SalesmanTest {

    @Test
    void givenAValidParams_whenCreateASalesman_thenReturnASalesmanCreated() {

        // Given
        final var expectedName = "John";
        final var expectedCpf = "12345678900";
        final var expectedEmail = "jonh@gmail.com";
        final var expectedPhone = "(88) 99797 9990";

        // When
        final var actualSalesman = Salesman.of(
                        expectedName,
                        expectedCpf,
                        expectedEmail,
                        expectedPhone
                );

        // Then
        assertNotNull(actualSalesman);
        assertNotNull(actualSalesman.getId());
        assertEquals(expectedName, actualSalesman.getName());
        assertEquals(expectedCpf, actualSalesman.getCpf());
        assertEquals(expectedEmail, actualSalesman.getEmail());
        assertEquals(expectedPhone, actualSalesman.getPhone());

    }

}