package com.sparta.currency_user.controller;


import com.sparta.currency_user.dto.Exchange.RequestExchangeDto;
import com.sparta.currency_user.dto.Exchange.ResponseExchangeDto;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
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

    // TODO: 세션 인증/인가로 변경 시 URI 수정 필요
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ResponseExchangeDto>> findAllExchangesByUser(@PathVariable Long userId){
        return ResponseEntity.ok().body(
                exchangeService.findAllExchangesByUser(userId)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateExchangeStatus(@PathVariable Long id){
        exchangeService.updateExchangeStatus(id);
        return ResponseEntity.ok().body("환전 요청이 취소 되었숩니다.");
    }


}
