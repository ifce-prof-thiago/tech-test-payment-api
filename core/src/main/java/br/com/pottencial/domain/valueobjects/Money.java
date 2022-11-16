package br.com.pottencial.domain.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money implements Serializable {

    public static final Money ZERO = Money.of(BigDecimal.ZERO);

    private final BigDecimal amount;

    private Money(final BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount, "'amount' should not be null");
    }

    public static Money of(final BigDecimal amount) {
        return new Money(scale(amount));
    }

    public static Money of(final String amount) {
        return new Money(scale(new BigDecimal(amount)));
    }

    public boolean isGreaterThanZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(final Money other) {
        return amount.compareTo(other.amount) > 0;
    }

    public Money add(final Money other) {
        return Money.of(amount.add(other.amount));
    }

    public Money subtract(final Money other) {
        return Money.of(amount.subtract(other.amount));
    }

    public Money multiply(final BigDecimal other) {
        return Money.of(amount.multiply(other));
    }

    public Money multiply(final int value) {
        return Money.of(amount.multiply(BigDecimal.valueOf(value)));
    }


    public BigDecimal amount() {
        return amount;
    }

    private static BigDecimal scale(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
