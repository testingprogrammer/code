package com.coding.challenge.prices.infraestructure.adapters.outbound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.coding.challenge.prices.application.ports.outbound.PriceRepositoryPort;
import com.coding.challenge.prices.domain.model.Price;
import com.coding.challenge.prices.infraestructure.mapper.PriceMapperInfraestructure;

@Repository
public class PriceJpaRepositoryAdapter implements PriceRepositoryPort {

	private final PriceJpaRespository priceJpaRespository;

	public PriceJpaRepositoryAdapter(PriceJpaRespository priceJpaRespository) {
		this.priceJpaRespository = priceJpaRespository;

	}
	@Override
	public List<Price> findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(Long productId, Long brandId, LocalDateTime date) {
		return priceJpaRespository
				.findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(productId, brandId, date)
				.stream()
				.map(PriceMapperInfraestructure.INSTANCE::entityToModel)
				.collect(Collectors.toList());
	}

}
