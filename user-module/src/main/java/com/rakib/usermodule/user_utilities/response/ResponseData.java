package com.rakib.usermodule.user_utilities.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
    public static Mono<ResponseEntity<?>> responseEntityMono(HttpStatus status, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", status.value());
        response.put("message", message);
        response.put("data", data);
        return Mono.just(ResponseEntity.ok().body(response));
    }

    public static Flux<ResponseEntity<?>> responseEntityFlux(HttpStatus status, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", status.value());
        response.put("message", message);
        response.put("data", data);
        return Flux.just(ResponseEntity.ok().body(response));
    }
}
