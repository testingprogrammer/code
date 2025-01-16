package com.coding.challenge.prices.infraestructure.adapters.outbound;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.coding.challenge.prices.application.ports.outbound.PriceRepositoryPort;
import com.coding.challenge.prices.domain.model.Price;
import com.coding.challenge.prices.infraestructure.mapper.PriceMapper;

@Repository
public class PriceJpaRepositoryAdapter implements PriceRepositoryPort {

	private final PriceJpaRespository priceJpaRespository;

	public PriceJpaRepositoryAdapter(PriceJpaRespository priceJpaRespository) {
		this.priceJpaRespository = priceJpaRespository;

	}
 	@Override
	public Optional<Price> findPriceByProductIdAndBrandIdApplicableOnDate(Long productId, Long brandId,
			LocalDateTime date) {
		 return priceJpaRespository
				 .findPriceByProductIdAndBrandIdApplicableOnDate(productId, brandId, date)
				 .map(PriceMapper.INSTANCE::entityToModel);
	}
}
