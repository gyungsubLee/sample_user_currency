package com.sparta.currency_user.dto.currency;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull(message = "통화명을 입력해주세요")
    private String currencyName;

    @NotNull(message = "환율 정보를 입력해주세요")
    private BigDecimal exchangeRate;

    @NotNull(message = "통화 단위를 입력해주세요")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
