package br.com.pottencial.application.sales.database;

import br.com.pottencial.domain.entity.Salesman;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeDatabaseSalesman {

    private static final Map<String, Salesman> SALESMAN_REPOSITORY = new HashMap<>();

    static {

        final var s1 = Salesman.of(
                "4d881744-e2a3-4696-b74b-a92685ca48cf",
                "John Doe",
                "123.456.789-10",
                "j@email.com",
                "123456789"
        );

        SALESMAN_REPOSITORY.put(s1.getId().toString(), s1);

    }

    public static Optional<Salesman> getById(String salesmanId) {

        final var salesman = SALESMAN_REPOSITORY.get(salesmanId);

        return Optional.ofNullable(salesman);

    }

}
