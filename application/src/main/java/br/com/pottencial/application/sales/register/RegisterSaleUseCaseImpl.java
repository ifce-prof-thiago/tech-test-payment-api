package br.com.pottencial.application.sales.register;

import br.com.pottencial.application.sales.database.FakeDatabaseProduct;
import br.com.pottencial.application.sales.database.FakeDatabaseSalesman;
import br.com.pottencial.domain.entity.Sale;
import br.com.pottencial.domain.entity.SaleItem;
import br.com.pottencial.domain.entity.Salesman;

import java.util.ArrayList;
import java.util.List;

public class RegisterSaleUseCaseImpl implements RegisterSaleUseCase {

    @Override
    public RegisterSaleOut execute(RegisterSaleIn anIn) {

        final var sale = Sale.of(
                anIn.orderId(),
                getSalesmanById(anIn.salesmanId()),
                getSaleItems(anIn)
        );

        return new RegisterSaleOut(
                sale.getId().toString(),
                sale.getTotal().amount(),
                sale.getStatus()
        );
    }

    private Salesman getSalesmanById(String salesmanId) {
        return FakeDatabaseSalesman.getById(salesmanId).orElseThrow(
                () -> new RuntimeException("Salesman not found")
        );
    }

    private List<SaleItem> getSaleItems(RegisterSaleIn anIn) {
        return anIn.items()
                .stream()
                .map(item -> {
                    final var p = FakeDatabaseProduct.getById(item.productId()).orElseThrow(
                            () -> new RuntimeException("Product not found")
                    );
                    return SaleItem.of(p.name(), p.price(), item.qty());
                })
                .toList();

    }

}
