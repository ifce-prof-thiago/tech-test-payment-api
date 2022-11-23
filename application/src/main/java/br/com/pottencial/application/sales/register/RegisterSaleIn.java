package br.com.pottencial.application.sales.register;

import java.util.List;

public record RegisterSaleIn(String orderId, String salesmanId, List<Item> items) {

    public record Item(String productId, Integer qty) {

    }

}