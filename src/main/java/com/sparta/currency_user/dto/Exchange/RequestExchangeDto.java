package com.sparta.currency_user.dto.Exchange;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RequestExchangeDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long currencyId;

    @NotNull( message = "환전할 금액을 입력 해주세요" )
    private BigDecimal amountInKrw;
}
