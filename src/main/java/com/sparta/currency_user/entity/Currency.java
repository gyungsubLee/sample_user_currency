package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table( name = "currency" )
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Currency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;

    @OneToMany(mappedBy = "to_currency_id")
    private List<Currency> currencyList = new ArrayList<>();

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }
}
