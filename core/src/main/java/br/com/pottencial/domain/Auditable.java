package br.com.pottencial.domain;

import java.time.Instant;

import static java.time.Instant.now;
import static java.util.Objects.requireNonNull;

public interface Auditable {
    Instant createdAt();

    Instant updatedAt();

    Instant deletedAt();

    default boolean isDeleted() {
        return this.deletedAt() != null;
    }

    static Auditable of(Instant createdAt, Instant updatedAt, Instant deletedAt) {

        requireNonNull(createdAt, "'createdAt' should not be null");
        requireNonNull(updatedAt, "'updatedAt' should not be null");

        return new AuditableRecord(createdAt, updatedAt, deletedAt);
    }

    static Auditable of() {
        final var now = now();
        return new AuditableRecord(now, now, null);
    }

    static Auditable ofUpdated(Auditable auditable) {
        return of(auditable.createdAt(), now(), auditable.deletedAt());
    }

    static Auditable ofDeleted(Auditable auditable) {

        if (!auditable.isDeleted()) {
            final var now = now();
            return Auditable.of(auditable.createdAt(), now, now);
        }

        return auditable;

    }

    static Auditable ofRestored(Auditable auditable) {
        return of(auditable.createdAt(), now(), null);
    }

    record AuditableRecord(Instant createdAt, Instant updatedAt, Instant deletedAt) implements Auditable {
    }

}



