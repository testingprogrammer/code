package com.coding.challenge.prices.application.usecases.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.coding.challenge.prices.PriceObjectMother;
import com.coding.challenge.prices.application.dto.PriceRequestDTO;
import com.coding.challenge.prices.application.ports.outbound.PriceRepositoryPort;
import com.coding.challenge.prices.application.usecases.GetPriceUseCase;
import com.coding.challenge.prices.domain.exception.PriceNotFoundException;

public class GetPriceUseCaseTest {

	private static final String EUR = "EUR";

	private static final double PRICE_25_45 = 25.45;

	private static final double PRICE_35_50 = 35.50;

	private static final long PRODUCT_ID = 35455L;

	private static final long BRAND_ID = 1L;

	private static final String DATE_2020_06_14T10_00_00 = "2020-06-14T00:00:00";
	private static final String DATE_2020_06_14T18_30_00 = "2020-06-14T18:30:00";
	private static final String DATE_2025_01_10T00_00_00 = "2025-01-10T00:00:00";


	@Mock
	private PriceRepositoryPort priceRepository;

	@InjectMocks
	private GetPriceUseCase sut;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void get_price_when_date_20200614_1000_and_productId_35455_and_brand_1_should_return_right_price() {
		// given
		var request = new PriceRequestDTO(LocalDateTime.parse(DATE_2020_06_14T10_00_00),BRAND_ID,PRODUCT_ID);
		var pricesFromQueryRespository = List.of(PriceObjectMother.createPrice().get(0));
		
		// when
		when(priceRepository.findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(
				request.productId(),
				request.brandId(),
				request.date())).thenReturn(pricesFromQueryRespository);
		// then
		var response = sut.getPrice(request);
		assertNotNull(response);
		assertEquals(BigDecimal.valueOf(PRICE_35_50), response.amount());
		assertEquals(EUR, response.currency());
		verify(priceRepository).findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(
				request.productId(),
				request.brandId(),
				request.date());
	}

	@Test
	void get_price_when_date_20200614_1830_and_productId_35455_and_brand_1_desambiguator_should_return_right_price() {
		// given
		var request = new PriceRequestDTO(LocalDateTime.parse(DATE_2020_06_14T18_30_00),BRAND_ID,PRODUCT_ID);
		var pricesFromQueryRespository = List.of(PriceObjectMother.createPrice().get(0), PriceObjectMother.createPrice().get(1));
		
		// when
		when(priceRepository.findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(
				request.productId(),
				request.brandId(),
				request.date())).thenReturn(pricesFromQueryRespository);
		// then
		var response = sut.getPrice(request);
		assertNotNull(response);
		assertEquals(BigDecimal.valueOf(PRICE_25_45), response.amount());
		assertEquals(EUR, response.currency());
		verify(priceRepository).findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(
				request.productId(),
				request.brandId(),
				request.date());
	}

	
	@Test
	 void get_price_when_wrong_date_should_return_throw_price_not_found_exception() {
		// given
		var request = new PriceRequestDTO(LocalDateTime.parse(DATE_2025_01_10T00_00_00),BRAND_ID,PRODUCT_ID);
		// when
		when(priceRepository.findPricesByProductIdAndBrandIAndDateBetweenStartDateAndEndDate(
				request.productId(),
				request.brandId(),
				request.date()))
				.thenReturn(Collections.emptyList());
		// then
		assertThrows(PriceNotFoundException.class, () -> sut.getPrice(request));
	}
	
	@Test
	 void get_price_when_null_params_should_return_throw_illegal_argument_exception() {
		assertThrows(IllegalArgumentException.class, () -> sut.getPrice(null));
	}
}
