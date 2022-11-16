package br.com.pottencial.application.sales.register;

import java.math.BigDecimal;
import java.util.List;

public class RegisterSaleUserCaseImpl implements RegisterSaleUseCase {

    @Override
    public RegisterSaleOut execute(final RegisterSaleIn anIn) {

        final var orderId = anIn.orderId();

        final var salesman = new RegisterSalesmanOut(
                "123",
                "John Doe",
                "j@gmail.com",
                "123456789"
        );

        final var items = List.of(
                new RegisterSaleItemOut(
                        "Mouse",
                        10,
                        BigDecimal.valueOf(10.0)
                ),
                new RegisterSaleItemOut(
                        "Teclado",
                        2,
                        BigDecimal.valueOf(20.0)
                )
        );

        return new RegisterSaleOut(
                orderId,
                salesman,
                items
        );

    }
}
