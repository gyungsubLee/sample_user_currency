package com.sparta.currency_user.dto.user;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotNull(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "이메일을 입력 해주세요.")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "유효한 이메일 형식이 아닙니다."
    )
    private String email;

//    public User toEntity() {
//        return new User(
//                this.name,
//                this.email
//        );
//    }
}
