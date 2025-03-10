package com.coding.challenge.prices.infraestructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.coding.challenge.prices.domain.model.Price;
import com.coding.challenge.prices.infraestructure.adapters.inbound.dto.PriceResponseDTO;
import com.coding.challenge.prices.infraestructure.adapters.outbound.entities.PriceEntity;
 

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceMapper {
	PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
	
	@Mapping(target = "money.amount", source = "price")
	@Mapping(target = "money.currency", source = "currency")
	Price entityToModel(PriceEntity price);
	
	@Mapping(target = "amount", source = "money.amount")
	@Mapping(target = "currency", source = "money.currency")
	PriceResponseDTO domainToDto(Price price);
}
