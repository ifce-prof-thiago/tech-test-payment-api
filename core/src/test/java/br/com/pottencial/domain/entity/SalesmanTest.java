package br.com.pottencial.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesmanTest {

    @Test
    void givenAValidParams_whenCreateASalesman_thenReturnASalesmanCreated() {

        // Given
        final var expectedName = "John";
        final var expectedCpf = "123.456.789-00";
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
        assertEquals(expectedCpf, actualSalesman.getCPF());
        assertEquals(expectedEmail, actualSalesman.getEmail());
        assertEquals(expectedPhone, actualSalesman.getPhone());

    }

    @Test
    void givenAnInvalidNullName_whenCallCreateSalesman_thenThrowDomainException() {
        // Given
        final var expectedErrorMessage = "'name' should not be null";

        // When
        final var actualException =
                assertThrows(RuntimeException.class,
                        () -> Salesman.of(
                                null,
                                "12=y3.456.789-00",
                                "j@gmail.com",
                                "(88) 99797 9990"
                        )
                );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    void givenAnInvalidEmptyName_whenCallCreateSalesman_thenThrowDomainException() {
        // Given
        final var expectedErrorMessage = "'name' should not be blank";

        // When
        final var actualException =
                assertThrows(RuntimeException.class,
                        () -> Salesman.of(
                                "      ",
                                "12345678900",
                                "j@gmail.com",
                                "(88) 99797 9990"
                        )
                );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    void givenAnInvalidNullCPF_whenCallCreateSalesman_thenThrowDomainException() {
        // Given
        final var expectedErrorMessage = "'CPF' should not be null";

        // When
        final var actualException =
                assertThrows(RuntimeException.class,
                        () -> Salesman.of(
                                "John",
                                null,
                                "j@gmail.com",
                                "(88) 99797 9990"
                        )
                );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    void givenAnInvalidRegexCPF_whenCallCreateSalesman_thenThrowDomainException() {
        // Given
        final var expectedErrorMessage = "'CPF' should be valid";

        // When
        final var actualException =
                assertThrows(RuntimeException.class,
                        () -> Salesman.of(
                                "John",
                                "123.456.08-90",
                                "j@gmail.com",
                                "(88) 99797 9990"
                        )
                );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

    }


}