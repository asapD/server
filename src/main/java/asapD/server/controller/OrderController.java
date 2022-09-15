package asapD.server.controller;

import asapD.server.controller.dto.orders.OrdersResponseDto;
import asapD.server.controller.dto.orders.OrdersRequestDto;
import asapD.server.controller.dto.orders.SerialNumRequestDto;
import asapD.server.domain.Orders;
import asapD.server.response.ApiResponse;
import asapD.server.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

  private final OrdersService ordersService;

  @PostMapping
  public ResponseEntity<ApiResponse> createOrder(@RequestBody OrdersRequestDto request) {
    // authentication 에서 추출해야 하는 email
    String email = "";

    OrdersResponseDto response = ordersService.createOrders(email, request);
    return ResponseEntity.ok(
        ApiResponse.builder().message("create order success").data(response).build());
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<ApiResponse> getOrder(@PathVariable Long orderId) {
    Orders response = ordersService.getOrder(orderId);
    return ResponseEntity.ok(
        ApiResponse.builder().message("get order one success").data(response).build());
  }

  @PostMapping("/verify-serial")
  public ResponseEntity<ApiResponse> verifySerialNum(@RequestBody SerialNumRequestDto request) {
    ordersService.verifySerialNum(request);
    return ResponseEntity.ok(ApiResponse.builder().message("verify serialNum success").build());
  }

  @GetMapping
  public ResponseEntity<ApiResponse> getOrderAll() {
    String email = "";
    List<Orders> response = ordersService.getOrderAll(email);
    return ResponseEntity.ok(
        ApiResponse.builder().message("get order all success").data(response).build());
  }
}
