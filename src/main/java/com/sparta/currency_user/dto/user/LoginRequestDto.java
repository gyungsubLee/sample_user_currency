package com.sparta.currency_user.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    private String email;

    // TODO: 비밀번호 패턴 정의 전 2
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;
}