package com.example.acl.api;
import com.example.acl.service.AclOrderService; import com.example.domain.Order;
import org.springframework.http.MediaType; import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController @RequestMapping(value="/api/orders", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
  private final AclOrderService service;
  public OrderController(AclOrderService service){ this.service = service; }
  @GetMapping("/{id}") public Mono<Order> getOrder(@PathVariable String id){
    return service.getOrder(id);
  }
}