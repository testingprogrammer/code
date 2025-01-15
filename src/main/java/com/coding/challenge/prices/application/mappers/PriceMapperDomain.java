package com.coding.challenge.prices.application.mappers;

import  org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.coding.challenge.prices.application.dto.PriceResponseDTO;
import com.coding.challenge.prices.domain.model.Price;

@Mapper(componentModel = "spring")
public interface PriceMapperDomain {

	PriceMapperDomain INSTANCE = Mappers.getMapper(PriceMapperDomain.class);

	@Mapping(target = "amount", source = "money.amount")
	@Mapping(target = "currency", source = "money.currency")
	PriceResponseDTO domainToDto(Price price);
}
