package com.example.acl.api;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  public Mono<ResponseEntity<Map<String,Object>>> handleIllegalArgument(IllegalArgumentException ex){
    return Mono.just(ResponseEntity.badRequest().body(Map.of(
      "timestamp", Instant.now().toString(),
      "error", "VALIDATION_ERROR",
      "message", ex.getMessage()
    )));
  }
  @ExceptionHandler(RuntimeException.class)
  public Mono<ResponseEntity<Map<String,Object>>> handleRuntime(RuntimeException ex){
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
      "timestamp", Instant.now().toString(),
      "error", "UPSTREAM_UNAVAILABLE",
      "message", ex.getMessage()
    )));
  }
}