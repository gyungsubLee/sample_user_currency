package com.sparta.currency_user.dto.Exchange;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RequestExchangeDto {

    private Long userId;
    private BigDecimal amountInKrw;
}
