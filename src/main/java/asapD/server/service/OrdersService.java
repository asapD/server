package asapD.server.service;

import asapD.server.controller.dto.orders.SerialNumRequestDto;
import asapD.server.repository.ItemRepository;
import asapD.server.repository.MemberRepository;
import asapD.server.repository.OrdersItemRepository;
import asapD.server.repository.OrdersRepository;
import asapD.server.controller.dto.orders.OrdersResponseDto;
import asapD.server.domain.Delivery;
import asapD.server.domain.Member;
import asapD.server.domain.OrderItem;
import asapD.server.domain.Orders;
import asapD.server.controller.dto.orders.OrdersRequestDto;
import asapD.server.utils.RedisClient;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final OrdersItemRepository ordersItemRepository;
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;
  private final RedisClient redisClient;

  private final String prefix = "asapD20220904";

  @Transactional
  public OrdersResponseDto createOrders(String email, OrdersRequestDto dto) {
    // 회원 정보 조회
    Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
      throw new NoSuchElementException("no");
    });

    // 주문 상품 생성
    List<OrderItem> items = new ArrayList<>();

    dto.getItems().keySet().forEach(key -> itemRepository.findById(key)
        .ifPresent(item -> {
          OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), dto.getItems().get(key));
          items.add(orderItem);
        }));
    
    // 배달 정보 생성
    Delivery delivery = new Delivery();
    delivery.setDownId(1);

    // 주문 정보 생성
    Orders orders = Orders.createOrder(member, delivery, items);
  
    // 주문 저장
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

  public Orders getOrder(Long orderId) {
    return ordersRepository.findById(orderId).orElseThrow(() -> {
      throw new NoSuchElementException("no");
    });
  }

  public void verifySerialNum(SerialNumRequestDto request) {
    String key = prefix + request.getOrderId();

   String value = Optional.ofNullable(redisClient.getValue(key))
       .orElseThrow(() -> {
      throw new NoSuchElementException("time out");
    });

   if (!value.equals(request.getSerialNum())) {
     throw new IllegalStateException("not equal");
   }
  }

  public List<Orders> getOrderAll(String email) {
    Member member =  memberRepository.findByEmail(email)
        .orElseThrow(() -> {
          throw new NoSuchElementException("no");
        });

    return ordersRepository.findAllByMember(member);
  }
}
