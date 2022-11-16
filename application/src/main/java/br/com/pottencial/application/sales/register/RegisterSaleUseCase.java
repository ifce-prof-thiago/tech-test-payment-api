package br.com.pottencial.application.sales.register;

import java.math.BigDecimal;
import java.util.List;

public interface RegisterSaleUseCase {

    RegisterSaleOut execute(final RegisterSaleIn anIn);

    record RegisterSaleIn(
            String orderId,
            String salesmanId,
            List<RegisterSaleItemIn> items) {

    }

    record RegisterSaleItemIn(
            String productId,
            Integer qty) {

    }

    record RegisterSaleOut(
            String orderId,
            RegisterSalesmanOut salesman,
            List<RegisterSaleItemOut> items) {

    }

    record RegisterSalesmanOut(
            String cpf,
            String name,
            String email,
            String phone) {

    }

    record RegisterSaleItemOut(
            String product,
            Integer qty,
            BigDecimal price) {

    }

}
