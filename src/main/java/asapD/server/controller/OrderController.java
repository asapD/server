package asapD.server.controller;

import asapD.server.controller.dto.OrdersResponseDto;
import asapD.server.controller.dto.OrdersRequestDto;
import asapD.server.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
}
