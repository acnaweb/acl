package com.example.acl.api;
import com.example.acl.service.AclOrderService;
import com.example.domain.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
  private final AclOrderService aclOrderService;
  public OrderController(AclOrderService aclOrderService){ this.aclOrderService = aclOrderService; }
  @GetMapping("/{id}")
  public Mono<Order> getOrder(@PathVariable String id){ return aclOrderService.getOrder(id); }
}