package com.coding.challenge.prices.application.usecases.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.coding.challenge.prices.PriceObjectMother;
import com.coding.challenge.prices.application.ports.outbound.PriceRepositoryPort;
import com.coding.challenge.prices.application.usecases.GetPriceUseCase;
import com.coding.challenge.prices.domain.exception.PriceNotFoundException;

class GetPriceUseCaseTest {

	private static final String EUR = "EUR";

	private static final double PRICE_35_50 = 35.50;

	private static final long PRODUCT_ID = 35455L;

	private static final long BRAND_ID = 1L;

	private static final String DATE_2020_06_14T10_00_00 = "2020-06-14T00:00:00";

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
		LocalDateTime date = LocalDateTime.parse(DATE_2020_06_14T10_00_00);
		// when
		when(priceRepository.findPriceByProductIdAndBrandIdApplicableOnDate(
				PRODUCT_ID, 
				BRAND_ID,
				date)).thenReturn(Optional.of(PriceObjectMother.createPrice()));
		// then
		var response = sut.getPrice(PRODUCT_ID, BRAND_ID, date);
		assertNotNull(response);
		assertEquals(BigDecimal.valueOf(PRICE_35_50), response.getMoney().amount());
		assertEquals(EUR, response.getMoney().currency().toString());
		verify(priceRepository).findPriceByProductIdAndBrandIdApplicableOnDate(
				PRODUCT_ID, 
				BRAND_ID,
				date);
	}

	@Test
	void get_price_when_wrong_date_should_return_throw_price_not_found_exception() {
		//given 
		LocalDateTime date = LocalDateTime.parse(DATE_2020_06_14T10_00_00);
		// when
		when(priceRepository.findPriceByProductIdAndBrandIdApplicableOnDate(
				PRODUCT_ID, 
				BRAND_ID,
				date)).thenReturn(Optional.empty());
		// then
		assertThrows(PriceNotFoundException.class, () -> sut.getPrice(PRODUCT_ID, BRAND_ID, date));
	}
	
	@Test
	void get_price_when_date_params_should_return_throw_price_not_found_exception() {
		//given 
		LocalDateTime date = LocalDateTime.parse(DATE_2020_06_14T10_00_00);
		// when
		when(priceRepository.findPriceByProductIdAndBrandIdApplicableOnDate(
				PRODUCT_ID, 
				BRAND_ID,
				date)).thenReturn(Optional.empty());
		// then
		assertThrows(PriceNotFoundException.class, () -> sut.getPrice(PRODUCT_ID,BRAND_ID, null));
	}
	
	@Test
	void get_price_when_null_params_should_return_throw_price_not_found_exception() {
		//given 
		LocalDateTime date = LocalDateTime.parse(DATE_2020_06_14T10_00_00);
		// when
		when(priceRepository.findPriceByProductIdAndBrandIdApplicableOnDate(
				PRODUCT_ID, 
				BRAND_ID,
				date)).thenReturn(Optional.empty());
		// then
		assertThrows(PriceNotFoundException.class, () -> sut.getPrice(null, null, null));
	}

}
