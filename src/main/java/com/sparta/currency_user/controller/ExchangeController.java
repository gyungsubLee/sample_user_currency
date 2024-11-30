package com.sparta.currency_user.controller;


import com.sparta.currency_user.dto.Exchange.RequestExchangeDto;
import com.sparta.currency_user.dto.Exchange.ResponseExchangeDto;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping("/currencies/{currencyId}")
    public ResponseEntity<ResponseExchangeDto> save(
            @PathVariable Long currencyId,
            @RequestBody RequestExchangeDto requestExchangeDto
            ) {

        // TODO: 세션 인증 인가 과정을 위해 유저 id를 getter로 넘겨줌
        return ResponseEntity.ok().body(
                exchangeService.save(
                        currencyId,
                        requestExchangeDto.getUserId(),
                        requestExchangeDto.getAmountInKrw()
                )
        );
    }



}
