package com.example.acl.mapper;
import com.example.acl.legacy.*; import com.example.domain.*;
import org.junit.jupiter.api.Test; import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class OrderMapperTest {
  @Test void shouldTranslateLegacyToDomain(){
    LegacyOrderResponse legacy = new LegacyOrderResponse("123",1,List.of(new LegacyOrderItem("SKU1",2,10.0)));
    Order o = OrderMapper.toDomain(legacy);
    assertEquals("123", o.id()); assertEquals(OrderStatus.APPROVED, o.status()); assertEquals(1, o.items().size());
  }
}