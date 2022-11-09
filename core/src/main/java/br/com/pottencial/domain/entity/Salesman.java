package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.Auditable;

import java.util.UUID;

public class Salesman extends AggregateRoot {

    private String name;
    private String CPF;
    private String email;
    private String phone;

    private Salesman(
            final UUID id,
            final String name,
            final String CPF,
            final String email,
            final String phone,
            final boolean enabled,
            final Auditable auditable) {

        super(id, enabled, auditable);
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.phone = phone;

    }

    public static Salesman of(
            final String name,
            final String CPF,
            final String email,
            final String phone) {

        final var id = UUID.randomUUID();
        final var auditable = Auditable.of();

        return new Salesman(id, name, CPF, email, phone, true, auditable);

    }

    @Override
    public void validate() {

    }

    public String getName() {
        return name;
    }

    public String getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
}
