package com.coding.challenge.prices.infraestructure.adapters.outbound;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coding.challenge.prices.infraestructure.adapters.outbound.entities.PriceEntity;

public interface PriceJpaRespository extends JpaRepository<PriceEntity, Long> {

	@Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId " + "AND p.brandId = :brandId "
			+ "AND :date BETWEEN p.startDate AND p.endDate")
	List<PriceEntity> findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(
			@Param("productId") Long productId,
			@Param("brandId") Long brandId, 
			@Param("date") LocalDateTime date);

}
