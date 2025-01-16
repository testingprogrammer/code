package com.coding.challenge.prices;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.coding.challenge.prices.domain.model.Currency;
import com.coding.challenge.prices.domain.model.Money;
import com.coding.challenge.prices.domain.model.Price;

public class PriceObjectMother {

    public static Price createPrice() {
    	
        return Price.builder()
                .brandId(1L)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceListId(1)
                .priority((short) 0)
                .productId(35455L)
                .money(new Money(BigDecimal.valueOf(35.50), Currency.EUR))
                .build() ;
    }
}
