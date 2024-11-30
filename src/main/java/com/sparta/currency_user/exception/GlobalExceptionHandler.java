package com.sparta.currency_user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        // 에러 응답 생성
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorMessage", ex.getReason());
        errorResponse.put("errorCode", ex.getStatusCode().toString());

        log.info("errorResponse {}", errorResponse);

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}