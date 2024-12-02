package com.sparta.currency_user.dto.Exchange;

import com.sparta.currency_user.dto.currency.CurrencyResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.enums.ExchangeStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ResponseExchangeDto {

    private Long id;
    private Long userId;
    private Long toCurrencyId;
    private BigDecimal amountInKR;
    private BigDecimal amountAfterExchange;
    private ExchangeStatus status;

    public ResponseExchangeDto(Exchange exchange) {
        this.id = exchange.getId();
        this.userId = exchange.getUser().getId();
        this.toCurrencyId = exchange.getCurrency().getId();
        this.amountInKR = exchange.getAmountInKrw();
        this.amountAfterExchange = exchange.getAmountInExchange();
        this.status = exchange.getStatus();
    }

    public static ResponseExchangeDto toDto(Exchange exchange){
        return new ResponseExchangeDto(exchange);
    }
}
