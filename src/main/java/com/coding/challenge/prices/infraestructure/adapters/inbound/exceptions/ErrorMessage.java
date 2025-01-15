package com.coding.challenge.prices.infraestructure.adapters.inbound.exceptions;

import java.time.Instant;

public record ErrorMessage(
	 Integer status,
	 Instant date,
	 String message,
	 String description
) {}
