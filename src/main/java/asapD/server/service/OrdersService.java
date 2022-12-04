package asapD.server.service;

import asapD.server.controller.dto.orders.OrderItemResponse;
import asapD.server.controller.dto.orders.OrdersInfoResponse;
import asapD.server.controller.dto.orders.OrdersRequest;
import asapD.server.controller.dto.orders.OrdersResponse;
import asapD.server.controller.dto.orders.SerialNumRequest;
import asapD.server.controller.exception.ApiException;
import asapD.server.controller.exception.ApiExceptionEnum;
import asapD.server.domain.Delivery;
import asapD.server.domain.Member;
import asapD.server.domain.OrderItem;
import asapD.server.domain.Orders;
import asapD.server.repository.ItemRepository;
import asapD.server.repository.MemberRepository;
import asapD.server.repository.OrdersRepository;
import asapD.server.utils.RedisClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static asapD.server.controller.exception.ApiExceptionEnum.*;

@Service
@RequiredArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;

  private final RedisClient redisClient;

  @Value("${serial.prefix}")
  private String prefix;

  @Transactional
  public OrdersResponse createOrders(String email, OrdersRequest dto) {

    Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
      throw new ApiException(MEMBER_NOT_FOUND_EXCEPTION);
    });

    List<OrderItem> items = new ArrayList<>();

    dto.getItems().keySet().forEach(key -> itemRepository.findById(Long.valueOf(key))
        .ifPresent(item -> {
          OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),
              dto.getItems().get(key));
          items.add(orderItem);
        }));

    Delivery delivery = Delivery.createDelivery(dto.getDestination());

    Orders orders = Orders.createOrder(member, delivery, items);

    Orders savedOrder = ordersRepository.save(orders);

    String serialNum = createSerialNum(savedOrder.getId(), member.getId());
    return new OrdersResponse(orders.getId(), serialNum);
  }

  private String createSerialNum(Long orderId, Long memberId) {

    String key = prefix + orderId;
    String serialNum = prefix + orderId + memberId;

    redisClient.setValue(key, serialNum, 10L);
    return serialNum;
  }

  public OrdersInfoResponse getOrder(Long orderId) {

    Orders order = ordersRepository.findById(orderId).orElseThrow(
            () -> new ApiException(ORDER_NOT_FOUND_EXCEPTION)
    );

    return new OrdersInfoResponse(order);

  }

  public void verifySerialNum(SerialNumRequest request) {

    String key = prefix + request.getOrderId();

    String value = Optional.ofNullable(redisClient.getValue(key))
        .orElseThrow(() -> {
          throw new ApiException(TIMEOUT_EXCEPTION);
        });

    if (!value.equals(request.getSerialNum())) {
      throw new ApiException(SERIALNUM_INVALID_EXCEPTION);
    }
  }

  public List<OrdersInfoResponse> getOrderAll(String email) {

    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> {
              throw new ApiException(MEMBER_NOT_FOUND_EXCEPTION);
            });

    return ordersRepository.findAllByMember(member).stream().map(OrdersInfoResponse::new).collect(Collectors.toList());
  }
}
