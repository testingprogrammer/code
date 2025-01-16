package com.coding.challenge.prices.application.ports.outbound;

import java.time.LocalDateTime;
import java.util.Optional;

import com.coding.challenge.prices.domain.model.Price;

public interface PriceRepositoryPort {
	
	public Optional<Price> findPriceByProductIdAndBrandIdApplicableOnDate(Long productId, Long brandId, LocalDateTime date);

}
