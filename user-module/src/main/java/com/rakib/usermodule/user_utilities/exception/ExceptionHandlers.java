package com.rakib.usermodule.user_utilities.exception;


import com.rakib.usermodule.user_utilities.exception.classes.AlreadyExistException;
import com.rakib.usermodule.user_utilities.exception.classes.InvalidKeyException;
import com.rakib.usermodule.user_utilities.exception.classes.NotAllowException;
import com.rakib.usermodule.user_utilities.exception.classes.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(AlreadyExistException.class)
    public Mono<ResponseEntity<Object>> handleException(AlreadyExistException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("AlreadyExist")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build())));
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<Object>> handleException(NotFoundException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("NotFound")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build())));
    }

    @ExceptionHandler(InvalidKeyException.class)
    public Mono<ResponseEntity<Object>> handleException(InvalidKeyException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("InvalidKey")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build())));
    }

    @ExceptionHandler(NotAllowException.class)
    public Mono<ResponseEntity<Object>> handleException(NotAllowException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("Not Allow")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build())));
    }
}
