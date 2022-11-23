package br.com.pottencial.application.sales.database;

import br.com.pottencial.domain.valueobjects.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeDatabaseProduct {

    private static final Map<String, Product> PRODUCT_REPOSITORY = new HashMap<>();

    static {

        final var p1 = new Product(
                "6e8df1be-9291-4ed7-bebb-405c0d02136c",
                "Máquina de Lavar",
                Money.of("1200.00")
        );

        final var p2 = new Product(
                "6b2f4db0-de20-4c91-ac40-8bfbfd5d39d4",
                "Play Station 5",
                Money.of("5400.00")
        );

        final var p3 = new Product(
                "c0b5b0a1-0b1f-4b9f-8b9a-5c1b0b5c0b5c",
                "Geladeira",
                Money.of("2000.00")
        );

        final var p4 = new Product(
                "c0b5b0a2-0b1f-4b9f-8b9a-5c1b0b5c0b5c",
                "Bicicleta Aro 29",
                Money.of("3000.00")
        );

        PRODUCT_REPOSITORY.put(p1.id(), p1);
        PRODUCT_REPOSITORY.put(p2.id(), p2);
        PRODUCT_REPOSITORY.put(p3.id(), p3);
        PRODUCT_REPOSITORY.put(p4.id(), p4);

    }

    public record Product(String id, String name, Money price) {

    }

    public static Optional<Product> getById(String productId) {

        final var product = PRODUCT_REPOSITORY.get(productId);

        return Optional.ofNullable(product);

    }

}
