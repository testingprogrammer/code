package com.coding.challenge.prices.domain.model;

import java.math.BigDecimal;

public record Money(BigDecimal amount, Currency currency) {
}
