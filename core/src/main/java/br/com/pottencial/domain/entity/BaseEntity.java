package br.com.pottencial.domain.entity;

import br.com.pottencial.domain.Auditable;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public abstract class BaseEntity {

    private final UUID id;
    private Auditable auditable;

    protected BaseEntity(final UUID id, final Auditable auditable) {
        this.id = requireNonNull(id, "'id' should not be null");
        this.auditable = requireNonNull(auditable, "'auditable' should not be null");
    }

    public abstract void validate();

    public UUID getId() {
        return id;
    }

    public final void delete() {
        this.auditable = Auditable.ofDeleted(this.auditable);
    }

    public final void restore() {
        this.auditable = Auditable.ofRestored(this.auditable);
    }

    public final void update() {
        this.auditable = Auditable.ofUpdated(this.auditable);
    }

    public final Auditable getAuditable() {
        return auditable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
