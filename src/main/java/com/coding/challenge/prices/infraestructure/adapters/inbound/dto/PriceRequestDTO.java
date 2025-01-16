package com.coding.challenge.prices.infraestructure.adapters.inbound.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record PriceRequestDTO(
    @DateTimeFormat(iso = ISO.DATE_TIME)
    LocalDateTime date,
    Long productId,
    Long brandId
) {}
