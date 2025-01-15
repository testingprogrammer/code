package com.coding.challenge.prices.application.ports.inbound;

import com.coding.challenge.prices.application.dto.PriceRequestDTO;
import com.coding.challenge.prices.application.dto.PriceResponseDTO;

public interface GetPriceUseCasePort {
	PriceResponseDTO getPrice(PriceRequestDTO request);
}
