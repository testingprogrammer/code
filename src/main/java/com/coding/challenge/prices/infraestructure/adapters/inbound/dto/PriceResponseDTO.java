package com.coding.challenge.prices.infraestructure.adapters.inbound.dto;

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
