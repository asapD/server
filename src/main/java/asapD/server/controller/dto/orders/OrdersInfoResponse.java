package asapD.server.controller.dto.orders;

import java.util.List;
import java.util.stream.Collectors;

import asapD.server.domain.Orders;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersInfoResponse {

  private long orderId;
  private List<OrderItemResponse> orderItemList;
  private long deliveryId;
  private String destination;

  public OrdersInfoResponse(Orders orders) {
    orderId = orders.getId();
    orderItemList = orders.getOrderItems().stream().map(OrderItemResponse::new).collect(Collectors.toList());
    deliveryId = orders.getDelivery().getId();
    destination = orders.getDelivery().getDestination();
  }
}
