package com.example.acl.service;
import com.example.domain.Order;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class CachedAclOrderService {
  private final AclOrderService delegate;
  public CachedAclOrderService(AclOrderService delegate){ this.delegate = delegate; }
  @Cacheable(value = "orders", key = "#id")
  public Mono<Order> getOrderCached(String id){
    return delegate.getOrder(id);
  }
}