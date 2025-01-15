package com.coding.challenge.prices.application.usecases;

import java.util.Comparator;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coding.challenge.prices.application.dto.PriceRequestDTO;
import com.coding.challenge.prices.application.dto.PriceResponseDTO;
import com.coding.challenge.prices.application.mappers.PriceMapperDomain;
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

	public PriceResponseDTO getPrice(PriceRequestDTO request) {
		if (Objects.isNull(request)) {
			LOGGER.error("getPrice#> request cant be null", request);

			throw new IllegalArgumentException("Request cant be null");
		}
		
		LOGGER.info("getPrice#> Request for price with parameters {}", request);
		
		return priceRepository.findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(request.productId(), request.brandId(),request.date())
				.stream()
                .max(Comparator.comparing(Price::getPriority))
				.map(PriceMapperDomain.INSTANCE::domainToDto)
				.orElseThrow(() -> new PriceNotFoundException("Price not found"));
	}
}