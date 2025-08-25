package com.example.acl.service;
import com.example.acl.legacy.*; import com.example.acl.mapper.OrderMapper; import com.example.domain.Order;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.Duration;
@Service
public class AclOrderService {
  private final LegacyClient legacyClient;
  public AclOrderService(LegacyClient legacyClient){ this.legacyClient = legacyClient; }
  @TimeLimiter(name="legacy") @CircuitBreaker(name="legacy", fallbackMethod="fallbackOrder") @Retry(name="legacy")
  public Mono<Order> getOrder(String id){
    return legacyClient.getOrderById(id).timeout(Duration.ofSeconds(3)).map(OrderMapper::toDomain);
  }
  private Mono<Order> fallbackOrder(String id, Throwable t){
    return Mono.error(new RuntimeException("Fallback para order " + id, t));
  }
}