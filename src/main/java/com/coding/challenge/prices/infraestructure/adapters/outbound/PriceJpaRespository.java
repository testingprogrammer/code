package com.coding.challenge.prices.infraestructure.adapters.outbound;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import com.coding.challenge.prices.infraestructure.adapters.outbound.entities.PriceEntity;

public interface PriceJpaRespository extends JpaRepository<PriceEntity, Long> {

	@NativeQuery(value = "SELECT * FROM PRICES p WHERE p.product_id = :productId "
			+ "AND p.brand_id = :brandId AND :date BETWEEN p.start_date AND p.end_date ORDER BY p.priority DESC LIMIT 1")
	Optional<PriceEntity> findPriceByProductIdAndBrandIdApplicableOnDate(
			@Param("productId") Long productId, @Param("brandId") Long brandId, @Param("date") LocalDateTime date);
}
