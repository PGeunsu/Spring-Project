package Spring.Project.entity.order;

import Spring.Project.entity.base.BaseEntity;
import Spring.Project.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count; //수량

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice()); //상품 가격을 주문가격으로 세팅
        item.removeStock(count); //removeStock 메서드를 사용하여 재고를 제거
        return orderItem;
    }
    public int getTotalPrice(){
        return orderPrice * count;
    } //주문 가격과 주문 수량을 곱하여 해당 상품의 총 가격을 구함
    public void cancel(){
        this.getItem().addStock(count);
    } //주문 취소 시 주문 수량 만큼 상품의 재고를 더해줌


}
