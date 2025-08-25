package com.example.acl.mapper;
import com.example.acl.legacy.LegacyOrderItem;
import com.example.acl.legacy.LegacyOrderResponse;
import com.example.domain.*;
import java.util.List;
public final class OrderMapper {
  private OrderMapper() {}
  public static Order toDomain(LegacyOrderResponse legacy){
    return new Order(legacy.id(), mapStatus(legacy.status()), mapItems(legacy.items()));
  }
  private static OrderStatus mapStatus(int statusCode){
    return switch (statusCode) {
      case 0 -> OrderStatus.PENDING;
      case 1 -> OrderStatus.APPROVED;
      case 2 -> OrderStatus.REJECTED;
      default -> throw new IllegalArgumentException("Status legado desconhecido: " + statusCode);
    };
  }
  private static List<OrderItem> mapItems(List<LegacyOrderItem> items){
    return items.stream().map(i -> new OrderItem(i.sku(), i.qty(), i.price())).toList();
  }
}