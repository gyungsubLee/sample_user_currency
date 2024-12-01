package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.user.UserResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

    public UserResponseDto login(String email, String password) {
        Optional<User> findUser = authRepository.findByEmail(email);

        // 이메일에 해당하는 유저 검증
        if(findUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 회원이 존재하지 않습니다.");
        }

        // 유저 비밀번호 검증
        if(!isPasswordMatching(password, findUser.get().getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다.");
        }

        return UserResponseDto.toDto(findUser.get());
    }

    private boolean isPasswordMatching(String rawPassword, String storedPassword) {
        // TODO: security 검증으로 변경
            // sercurity 변경 시, 변수명 수정 storedPassword -> hashedPassword
        return storedPassword.equals(rawPassword);
    }

}
