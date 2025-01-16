package com.coding.challenge.prices.application.ports.inbound;

import java.time.LocalDateTime;

import com.coding.challenge.prices.domain.model.Price;

public interface GetPriceUseCasePort {
	Price getPrice(Long productId, Long brandId, LocalDateTime date);
}
