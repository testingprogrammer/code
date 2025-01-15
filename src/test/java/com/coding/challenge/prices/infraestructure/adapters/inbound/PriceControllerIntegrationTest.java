package com.coding.challenge.prices.infraestructure.adapters.inbound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import com.coding.challenge.prices.application.dto.PriceResponseDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceControllerIntegrationTest {

	private static final String EUR = "EUR";
	private static final String URI = "http://localhost:%d/api/v1/prices";
	private static final String PRODUCT_ID = "productId";
	private static final String BRAND_ID = "brandId";
	private static final String DATE = "date";
	@Autowired
	private TestRestTemplate client;
	@LocalServerPort
	private Integer port;
	private String url;

	@BeforeEach
	void setUp() {
		url = String.format(URI, port);
		
	}

	@Test
	void find_price_when_date_20200614_1000_and_productId_35455_and_brand_1_should_return_200_with_price_dto() {
		var priceExpected = new PriceResponseDTO(1L, LocalDateTime.parse("2020-06-14T00:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L,
				BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_EVEN),EUR);
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "2020-06-14T10:00:00")
				.queryParam(BRAND_ID, 1).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);
		PriceResponseDTO priceResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(priceResponse);
		assertEquals(priceExpected.brandId(), priceResponse.brandId());
		assertEquals(priceExpected.startDate(), priceResponse.startDate());
		assertEquals(priceExpected.endDate(), priceResponse.endDate());
		assertEquals(priceExpected.priceListId(), priceResponse.priceListId());
		assertEquals(priceExpected.productId(), priceResponse.productId());
		assertEquals(priceExpected.amount(), priceResponse.amount());

	}

	@Test
	void find_price_when_date_20200614_1600_and_productId_35455_and_brand_1_should_return_200_with_price_dto() {
		var priceExpected = new PriceResponseDTO(1L, LocalDateTime.parse("2020-06-14T15:00:00"),
				LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455L,
				BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_EVEN),EUR);
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "2020-06-14T16:00:00")
				.queryParam(BRAND_ID, 1).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);
		PriceResponseDTO priceResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(priceResponse);
		assertEquals(priceExpected.brandId(), priceResponse.brandId());
		assertEquals(priceExpected.startDate(), priceResponse.startDate());
		assertEquals(priceExpected.endDate(), priceResponse.endDate());
		assertEquals(priceExpected.priceListId(), priceResponse.priceListId());
		assertEquals(priceExpected.productId(), priceResponse.productId());
		assertEquals(priceExpected.amount(), priceResponse.amount());
	}

	@Test
	void find_price_when_date_20200614_2100_and_productId_35455_and_brand_1_should_return_200_with_price_dto() {
		var priceExpected = new PriceResponseDTO(1L, LocalDateTime.parse("2020-06-14T00:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L,
				BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_EVEN),EUR);
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "2020-06-14T21:00:00")
				.queryParam(BRAND_ID, 1).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);
		PriceResponseDTO priceResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(priceResponse);
		assertEquals(priceExpected.brandId(), priceResponse.brandId());
		assertEquals(priceExpected.startDate(), priceResponse.startDate());
		assertEquals(priceExpected.endDate(), priceResponse.endDate());
		assertEquals(priceExpected.priceListId(), priceResponse.priceListId());
		assertEquals(priceExpected.productId(), priceResponse.productId());
		assertEquals(priceExpected.amount(), priceResponse.amount());
	}

	@Test
	void find_price_when_date_20200615_1000_and_productId_35455_and_brand_1_should_return_200_with_price_dto() {
		var priceExpected = new PriceResponseDTO(1L, LocalDateTime.parse("2020-06-15T00:00:00"),
				LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455L,
				BigDecimal.valueOf(30.50).setScale(2, RoundingMode.HALF_EVEN),EUR);
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "2020-06-15T10:00:00")
				.queryParam(BRAND_ID, 1).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);
		PriceResponseDTO priceResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(priceResponse);
		assertEquals(priceExpected.brandId(), priceResponse.brandId());
		assertEquals(priceExpected.startDate(), priceResponse.startDate());
		assertEquals(priceExpected.endDate(), priceResponse.endDate());
		assertEquals(priceExpected.priceListId(), priceResponse.priceListId());
		assertEquals(priceExpected.productId(), priceResponse.productId());
		assertEquals(priceExpected.amount(), priceResponse.amount());
	}

	@Test
	void find_price_when_date_20200616_2100_and_productId_35455_and_brand_1_should_return_200_with_price_dto() {
		var priceExpected = new PriceResponseDTO(1L, LocalDateTime.parse("2020-06-15T16:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455L,
				BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_EVEN),EUR);
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "2020-06-16T21:00:00")
				.queryParam(BRAND_ID, 1).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);
		PriceResponseDTO priceResponse = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(priceResponse);
		assertEquals(priceExpected.brandId(), priceResponse.brandId());
		assertEquals(priceExpected.startDate(), priceResponse.startDate());
		assertEquals(priceExpected.endDate(), priceResponse.endDate());
		assertEquals(priceExpected.priceListId(), priceResponse.priceListId());
		assertEquals(priceExpected.productId(), priceResponse.productId());
		assertEquals(priceExpected.amount(), priceResponse.amount());
	}

	@Test
	void find_price_when_date_20200616_2100_and_productId_35455_and_brand_2_should_return_404_not_found() {
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "2020-06-16T21:00:00")
				.queryParam(BRAND_ID, 2).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void find_price_when_request_is_null_should_return_404_not_found() {
		URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
 	void find_price_when_wrong_format_date_and_productId_35455_and_brand_1_should_return_200_with_price_dto() {
		{
		URI uri = UriComponentsBuilder.fromUriString(url).queryParam(DATE, "20210614T10:00:00")
				.queryParam(BRAND_ID, 1).queryParam(PRODUCT_ID, 35455).build().toUri();

		ResponseEntity<PriceResponseDTO> response = client.getForEntity(uri, PriceResponseDTO.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		}
    }
}
