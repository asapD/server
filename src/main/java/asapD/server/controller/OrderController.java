package asapD.server.controller;

import asapD.server.controller.dto.orders.OrdersResponseDto;
import asapD.server.controller.dto.orders.OrdersRequestDto;
import asapD.server.controller.dto.orders.SerialNumRequestDto;
import asapD.server.domain.Orders;
import asapD.server.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class OrderController {

  private final OrdersService ordersService;

  @PostMapping("/api/orders")
  public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody OrdersRequestDto request) {
    String email = "";
    return ResponseEntity.ok(ordersService.createOrders(email, request));
  }

  @GetMapping("/api/orders/{orderId}")
  public ResponseEntity<Orders> getOrder(@PathVariable Long orderId) {
    return ResponseEntity.ok(ordersService.getOrder(orderId));
  }

  @PostMapping("/api/orders/verify-serial")
  public ResponseEntity<Void> verifySerialNum(@RequestBody SerialNumRequestDto request) {
    ordersService.verifySerialNum(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/api/orders")
  public ResponseEntity<List<Orders>> getOrderAll() {
    String email = "";
    return ResponseEntity.ok(ordersService.getOrderAll(email));
  }
}
