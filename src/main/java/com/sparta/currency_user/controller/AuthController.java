package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.user.SignupRequestDto;
import com.sparta.currency_user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> singup (@RequestBody SignupRequestDto requestDto) {

        log.info("AuthController 실행");
        authService.signup(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입을 축하합니다.");
    }

}
