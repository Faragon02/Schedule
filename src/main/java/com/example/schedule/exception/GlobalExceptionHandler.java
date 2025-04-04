package com.example.schedule.exception;


import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //요청한 정보를 찾을 수 없습니다.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleIllegalArgumentException(EntityNotFoundException ex){
        return ResponseEntity.badRequest().body(" " + ex.getMessage());
    }

    //이미 가입된 정보입니다.
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("[409 Conflict]:" + ex.getMessage());
    }
    //계정이 존재 하지 않을때

    //비밀 번호 오류
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[401 Unauthorized]:" + ex.getMessage());
    }
    //조회 실패
    @ExceptionHandler(DataRetrievalFailureException.class)
    public ResponseEntity<String> handleDataRetrievalFailureException(DataRetrievalFailureException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[500 Unauthorized]:" + ex.getMessage());
    }
    // 잘못된 인수 입력
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[400 Bad Request]:" + ex.getMessage());
    }
    //비밀번호 불일치
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<String> handleIllegalArgumentException(PasswordMismatchException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[403 Bad Request]:" + ex.getMessage());
    }
   //로그인 하세요
   @ExceptionHandler(NotAuthenticatedException.class)
   public ResponseEntity<String> handleNotAuthenticatedException(NotAuthenticatedException ex){
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[404 Not Found]:" + ex.getMessage());
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArqumentValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
