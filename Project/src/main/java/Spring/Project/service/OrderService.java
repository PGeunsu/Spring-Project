package Spring.Project.service;

import Spring.Project.dto.order.OrderDto;
import Spring.Project.dto.order.OrderHistoryDto;
import Spring.Project.dto.order.OrderItemDto;
import Spring.Project.entity.Member;
import Spring.Project.entity.item.Item;
import Spring.Project.entity.item.ItemImg;
import Spring.Project.entity.order.Order;
import Spring.Project.entity.order.OrderItem;
import Spring.Project.repository.cartOrder.CartItemRepository;
import Spring.Project.repository.cartOrder.OrderRepository;
import Spring.Project.repository.item.ItemImgRepository;
import Spring.Project.repository.item.ItemRepository;
import Spring.Project.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;
    private final CartItemRepository cartItemRepository;

    public Long order(OrderDto orderDto, String email){
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);
        //현재 로그인한 회원의 이메일 정보를 이용하여 회원정보 조회
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        //주문할 상품 엔티티와 주문 수량을 이용하여 주문 상품 엔티티를 생성
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        //회원정보와 주문할 상품 리스트 정보를 이용하여 주문 엔티티를 생성
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistoryDto> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistoryDto.addOrderItemDto(orderItemDto);
            }
            orderHistoryDtos.add(orderHistoryDto);
        }
        return new PageImpl<OrderHistoryDto>(orderHistoryDtos, pageable, totalCount);
        //페이지 구현 객체를 생성하여 반환
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email){
        Member curMember = memberRepository.findByEmail(email);
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    } //주문 취소 상태로 변경하면 변경감지 기능에 의해서 트렌젝션이 끝날 때 update 쿼리 실행

    public Long orders(List<OrderDto> orderDtoList, String email){
        Member member = memberRepository.findByEmail(email);
        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderDto orderDto : orderDtoList){ //주문할 상품 리스트 생성
            Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);

            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }
        Order order = Order.createOrder(member, orderItemList);
        //현재 로그인한 회원과 주문 상품 목록을 이용하여 주문 엔티티 생성
        orderRepository.save(order);

        return order.getId();
    }

    public void deleteOrderItem(Long orderItemId){
        Order order = orderRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
        orderRepository.delete(order);
    }


}
