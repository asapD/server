package asapD.server.controller;

import asapD.server.config.security.config.SecurityUtil;
import asapD.server.controller.dto.orders.OrdersInfoResponse;
import asapD.server.controller.dto.orders.OrdersRequest;
import asapD.server.controller.dto.orders.OrdersResponse;
import asapD.server.controller.dto.orders.SerialNumRequest;
import asapD.server.response.BaseResponse;
import asapD.server.service.OrdersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  @ApiOperation(value = "주문 생성")
  @ApiResponses({
          @ApiResponse(code = 200, message = "주문 생성 성공"),
          @ApiResponse(code = 404, message = "등록된 유저가 아닙니다.")
  })
  public ResponseEntity<BaseResponse> createOrder(@RequestBody OrdersRequest request) {

    OrdersResponse response =
        ordersService.createOrders(SecurityUtil.getCurrentMemberId(), request);

    return ResponseEntity.ok(
        BaseResponse.builder().message("주문 생성 성공").data(response).build());
  }

  @GetMapping("/{orderId}")
  @ApiOperation(value = "주문 확인", notes = "주문 정보 확인")
  @ApiResponses({
          @ApiResponse(code = 200, message = "주문 정보 확인 성공"),
          @ApiResponse(code = 404, message = "요청한 자원이 없습니다.")
  })
  public ResponseEntity<BaseResponse> getOrder(@PathVariable Long orderId) {

    OrdersInfoResponse response = ordersService.getOrder(orderId);

    return ResponseEntity.ok(
        BaseResponse.builder().message("주문 정보 확인 성공").data(response).build());
  }

  @PostMapping("/verify-serial")
  @ApiOperation(value = "시리얼 넘버 검증")
  @ApiResponses({
          @ApiResponse(code = 200, message = "시리얼 넘버 검증 성공"),
          @ApiResponse(code = 403, message = "요청한 자원이 없습니다."),
          @ApiResponse(code = 400, message = "요청 변수를 확인해주세요.")
  })
  public ResponseEntity<BaseResponse> verifySerialNum(@RequestBody SerialNumRequest request) {

    ordersService.verifySerialNum(request);

    return ResponseEntity.ok(BaseResponse.builder().message("시리얼 넘버 검증 성공").build());
  }

  @GetMapping
  @ApiOperation(value = "사용자 주문 목록 확인", notes = "전체 주문 목록 확인")
  @ApiResponses({
          @ApiResponse(code = 200, message = "사용자 주문 목록 확인 성공"),
          @ApiResponse(code = 404, message = "등록된 유저가 아닙니다.")
  })
  public ResponseEntity<BaseResponse> getOrderAll() {

    List<OrdersInfoResponse> response =
        ordersService.getOrderAll(SecurityUtil.getCurrentMemberId());

    return ResponseEntity.ok(
        BaseResponse.builder().message("사용자 주문 목록 확인 성공").data(response).build());
  }
}
