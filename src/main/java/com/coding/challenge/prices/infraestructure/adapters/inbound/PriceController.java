package com.coding.challenge.prices.infraestructure.adapters.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.prices.application.ports.inbound.GetPriceUseCasePort;
import com.coding.challenge.prices.infraestructure.adapters.inbound.dto.PriceRequestDTO;
import com.coding.challenge.prices.infraestructure.adapters.inbound.dto.PriceResponseDTO;
import com.coding.challenge.prices.infraestructure.mapper.PriceMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/prices")
public class PriceController {
    private static final Logger LOGGER =  LoggerFactory.getLogger(PriceController.class);
	private final GetPriceUseCasePort getPriceUseCase;
	
	public PriceController(GetPriceUseCasePort getPriceUseCase) {
		this.getPriceUseCase = getPriceUseCase;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceResponseDTO> getPrice(@Valid PriceRequestDTO params) {
		PriceResponseDTO response = PriceMapper.INSTANCE.domainToDto(
				getPriceUseCase.getPrice(params.productId(),params.brandId(), params.date()));
		LOGGER.info("getPrice#> Price found {}", response);

		return ResponseEntity.ok(response);
	}
}
