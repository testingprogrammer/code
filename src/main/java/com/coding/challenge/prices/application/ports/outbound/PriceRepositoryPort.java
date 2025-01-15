package com.coding.challenge.prices.application.ports.outbound;

import java.time.LocalDateTime;
import java.util.List;

import com.coding.challenge.prices.domain.model.Price;

public interface PriceRepositoryPort {
	
	public List<Price> findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(Long productId, Long brandId, LocalDateTime date);

}
