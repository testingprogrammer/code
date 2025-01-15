package com.coding.challenge.prices.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponseDTO(
		Long brandId,
		LocalDateTime startDate,
		LocalDateTime endDate,
		Integer priceListId,
		Long productId,
		BigDecimal amount,
		String  currency) {
}
