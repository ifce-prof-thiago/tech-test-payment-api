package br.com.pottencial.application.sales.register;

import br.com.pottencial.domain.entity.Sale;

import java.math.BigDecimal;

public record RegisterSaleOut(String saleId, BigDecimal total, Sale.Status status) {

}
