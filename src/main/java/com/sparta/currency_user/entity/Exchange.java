package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.ExchangeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exchange")
public class Exchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name =  "user_id", nullable = false)
    private User user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal amountInKrw;

    @Column(nullable = false)
    private BigDecimal amountInExchange;

    @Enumerated(value = EnumType.STRING)
    private ExchangeStatus status;


    public Exchange(User user, Currency currency, BigDecimal amountInKrw, BigDecimal exchangeRate, ExchangeStatus status) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amountInKrw;
        this.amountInExchange = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);
        this.status = status;
    }

    //
    public void updateStatus(ExchangeStatus exchangeStatus) {
        this.status = exchangeStatus;
    }
}

