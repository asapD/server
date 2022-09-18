package asapD.server.service;

import asapD.server.controller.dto.orders.OrderItemResponseDto;
import asapD.server.controller.dto.orders.OrdersInfoResponseDto;
import asapD.server.controller.dto.orders.OrdersRequestDto;
import asapD.server.controller.dto.orders.OrdersResponseDto;
import asapD.server.controller.dto.orders.SerialNumRequestDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;

  private final RedisClient redisClient;

  private final String prefix = "asapD20220904";

  @Transactional
  public OrdersResponseDto createOrders(String email, OrdersRequestDto dto) {

    Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
      throw new ApiException(ApiExceptionEnum.MEMBER_NOT_FOUND_EXCEPTION);
    });

    List<OrderItem> items = new ArrayList<>();

    dto.getItems().keySet().forEach(key -> itemRepository.findById(Long.valueOf(key))
        .ifPresent(item -> {
          OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),
              dto.getItems().get(key));
          items.add(orderItem);
        }));

    Delivery delivery = Delivery.createDelivery();

    Orders orders = Orders.createOrder(member, delivery, items, dto.getDestination());

    Orders savedOrder = ordersRepository.save(orders);

    String serialNum = createSerialNum(savedOrder.getId(), member.getId());
    return new OrdersResponseDto(orders.getId(), serialNum);
  }

  private String createSerialNum(Long orderId, Long memberId) {

    String key = prefix + orderId;
    String serialNum = prefix + orderId + memberId;

    redisClient.setValue(key, serialNum, 10L);
    return serialNum;
  }

  public OrdersInfoResponseDto getOrder(Long orderId) {

    return ordersRepository.findById(orderId).map(orders ->
            OrdersInfoResponseDto.builder()
                .memberId(orders.getMember().getId())
                .deliveryId(orders.getDelivery().getId())
                .orderItemList(orders.getOrderItems().stream().map(orderItem ->
                        OrderItemResponseDto.builder()
                            .itemId(orderItem.getItem().getId())
                            .orderId(orderItem.getOrders().getId())
                            .orderPrice(orderItem.getOrderPrice())
                            .count(orderItem.getCount())
                            .build())
                    .collect(Collectors.toList()))
                .destination(orders.getDestination())
                .build())
        .orElseThrow(() -> {
          throw new ApiException(ApiExceptionEnum.NOT_FOUND_EXCEPTION);
        });
  }

  public void verifySerialNum(SerialNumRequestDto request) {

    String key = prefix + request.getOrderId();

    String value = Optional.ofNullable(redisClient.getValue(key))
        .orElseThrow(() -> {
          throw new ApiException(ApiExceptionEnum.TIMEOUT_EXCEPTION);
        });

    if (!value.equals(request.getSerialNum())) {
      throw new ApiException(ApiExceptionEnum.SERIALNUM_INVALID_EXCEPTION);
    }
  }

  public List<OrdersInfoResponseDto> getOrderAll(String email) {

    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> {
          throw new ApiException(ApiExceptionEnum.MEMBER_NOT_FOUND_EXCEPTION);
        });

    return ordersRepository.findAllByMember(member).stream().map(orders ->
            OrdersInfoResponseDto.builder()
                .memberId(orders.getMember().getId())
                .deliveryId(orders.getDelivery().getId())
                .orderItemList(orders.getOrderItems().stream().map(orderItem ->
                        OrderItemResponseDto.builder()
                            .itemId(orderItem.getItem().getId())
                            .orderId(orderItem.getOrders().getId())
                            .orderPrice(orderItem.getOrderPrice())
                            .count(orderItem.getCount())
                            .build())
                    .collect(Collectors.toList()))
                .destination(orders.getDestination())
                .build())
        .collect(Collectors.toList());
  }
}
