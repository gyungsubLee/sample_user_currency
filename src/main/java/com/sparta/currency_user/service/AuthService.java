package com.sparta.currency_user.service;

import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void signup(String name, String email, String password) {

        log.info("AuthService 실행");
        // TODO: 비밀번호 암호화
//        String encodedPassword = passwordEncoder.encode(password);
//        log.info("encoded password: {}", encodedPassword);

        User user = new User(name, email, password);
        authRepository.save(user);
    }
}
