package com.coding.challenge.prices.application.usecases;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coding.challenge.prices.application.ports.inbound.GetPriceUseCasePort;
import com.coding.challenge.prices.application.ports.outbound.PriceRepositoryPort;
import com.coding.challenge.prices.domain.exception.PriceNotFoundException;
import com.coding.challenge.prices.domain.model.Price;

@Service
public class GetPriceUseCase implements GetPriceUseCasePort {
    private static final Logger LOGGER =  LoggerFactory.getLogger(GetPriceUseCase.class);
	private final PriceRepositoryPort priceRepository;

	public GetPriceUseCase(PriceRepositoryPort priceRepository) {
		this.priceRepository = priceRepository;
	}

	public Price getPrice(Long productId, Long brandId, LocalDateTime date){
		LOGGER.info("getPrice#> Request for price with productId {}, brandId {} and date {}"
				,productId,brandId,date);
		
		return priceRepository.findPriceByProductIdAndBrandIdApplicableOnDate(productId, brandId,date)
				.orElseThrow(() -> new PriceNotFoundException("Price not found"));
	}
}