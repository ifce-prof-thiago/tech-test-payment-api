package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.Auditable;

import java.util.UUID;

public abstract class AggregateRoot extends BaseEntity {

    private boolean enabled;

    protected AggregateRoot(final UUID id, final boolean enabled, final Auditable auditable) {
        super(id, auditable);
        this.enabled = enabled;
    }

    public void enableTo(boolean enabled) {
        this.enabled = enabled;
        this.update();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}