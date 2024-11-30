package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.ExchangeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exchange")
public class Exchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name =  "user_id")
    private User user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "currency_id" )
    private Currency currency;

    private BigDecimal amountInKrw;
    private BigDecimal amountInExchange;
    private ExchangeStatus status;


    private Exchange(BigDecimal amountInKrw, BigDecimal amountInExchange, ExchangeStatus status) {
        this.amountInKrw = amountInKrw;
        this.amountInExchange = amountInExchange;
        this.status = status;
    }

    //연관관계 편의 메서드
    private void setUser(User user){
        this.user = user;
        user.getExchangetList().add(this);
    }

    private void setCurrency(Currency currency){
        this.currency = currency;
        currency.getExchangeList().add(this);
    }

    // 생성 메서드
    public static Exchange createExchange(
            User user,
            Currency currency,
            BigDecimal amountInKrw,
            BigDecimal amountInExchange,
            ExchangeStatus status
            ) {

        Exchange exchange = new Exchange(amountInKrw, amountInExchange, status);
        exchange.setUser(user);
        exchange.setCurrency(currency);

        return exchange;
    }

}

