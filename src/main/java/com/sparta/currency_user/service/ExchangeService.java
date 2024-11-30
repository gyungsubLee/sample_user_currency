package com.sparta.currency_user.service;


import com.sparta.currency_user.dto.Exchange.ResponseExchangeDto;
import com.sparta.currency_user.dto.currency.CurrencyResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public ResponseExchangeDto save(Long currencyId, Long userId, BigDecimal amountInKrw) {

        Currency findCurrency = currencyRepository.findById(currencyId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 통화 정보를 찾을 수 없습니다.");
        });

        User findUser = userRepository.findById(userId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 통화 정보를 찾을 수 없습니다.");
        });

        // 환율
        BigDecimal exchangeRate = findCurrency.getExchangeRate();
        log.info("환율 {}", exchangeRate);

        // 환전
        BigDecimal amountInExchange = amountInKrw.divide(exchangeRate,  2, RoundingMode.HALF_UP);
        log.info("환전 금액 {}", amountInExchange);

        // 생성메서드를 사용한 엔티티 생성
        Exchange exchange = Exchange.createExchange(findUser, findCurrency, amountInKrw, amountInExchange, ExchangeStatus.NORMAL);

        Exchange saveExchange = exchangeRepository.save(exchange);

        // 저장되는지 id 확인
        log.info("exchange id check {}", saveExchange.getId());

        return new ResponseExchangeDto(saveExchange);
    }

    public List<ResponseExchangeDto> findAllExchangesByUser(Long userId) {
        return exchangeRepository.findAllByUserId(userId).stream().map(ResponseExchangeDto::toDtto).toList();
    }

    @Transactional
    public void updateExchangeStatus(Long id) {
        Exchange findExchange = exchangeRepository.findByIdOrElseThrow(id);

        findExchange.updateStatus(ExchangeStatus.CANCEL);
    }
}
