package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.RequestExchangeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "request_exchange")
public class RequestExchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name =  "user_id")
    private User user_id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "currency_id" )
    private Currency to_currency_id;

    private BigDecimal amount_in_krw;
    private BigDecimal amount_in_exchange;
    private RequestExchangeStatus status;
}

