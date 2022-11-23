package br.com.pottencial.infrastructure.sales.controllers;

import br.com.pottencial.application.sales.register.RegisterSaleIn;
import br.com.pottencial.application.sales.register.RegisterSaleOut;
import br.com.pottencial.application.sales.register.RegisterSaleUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleController {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RegisterSaleOut createSale(@RequestBody RegisterSaleIn anIn) {

        final var useCase = new RegisterSaleUseCaseImpl();

        return useCase.execute(anIn);

    }

    @PutMapping
    void updateSale() {
        System.out.println("Sale updated");
    }

    @GetMapping
    void retrieveSaleById() {
        System.out.println("Sale retrieved by id");
    }

}
