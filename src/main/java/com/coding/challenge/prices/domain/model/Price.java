package com.coding.challenge.prices.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
 
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Price {
	private Long brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Integer priceListId;
	private Short priority;
	private Long productId;
	private Money money;
	 
}
