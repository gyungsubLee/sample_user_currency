package com.sparta.currency_user.service;


import com.sparta.currency_user.dto.Exchange.ResponseExchangeDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRepository exchangeRepository;

    @Transactional
    public ResponseExchangeDto save( Long userId,Long currencyId, BigDecimal amountInKrw) {

        User findUser = userRepository.findByIdOrElseThrow(userId);

        Currency findCurrency = currencyRepository.findByIdOrElseThrow(currencyId);


        // 환율
        BigDecimal exchangeRate = findCurrency.getExchangeRate();
        log.info("환율 {}", exchangeRate);


        // 환전 - 엔티티 생성자 내부에서 처리
        Exchange exchange = new Exchange(findUser, findCurrency, amountInKrw, exchangeRate, ExchangeStatus.NORMAL);

        Exchange saveExchange = exchangeRepository.save(exchange);

        // 저장되는지 id 확인
        log.info("exchange id check {}", saveExchange.getId());

        return new ResponseExchangeDto(saveExchange);
    }

    @Transactional(readOnly = true)
    public List<ResponseExchangeDto> findAllExchangesByUser(Long userId) {
        return exchangeRepository.findAllByUserId(userId).stream().map(ResponseExchangeDto::toDto).toList();
    }

    @Transactional
    public void updateExchangeStatus(Long id) {
        Exchange findExchange = exchangeRepository.findByIdOrElseThrow(id);

        findExchange.updateStatus(ExchangeStatus.CANCEL);
    }
}
