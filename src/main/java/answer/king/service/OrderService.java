package answer.king.service;

import answer.king.model.Item;
import answer.king.model.LineItem;
import answer.king.model.Order;
import answer.king.model.Receipt;
import answer.king.repo.ItemRepository;
import answer.king.repo.OrderRepository;
import answer.king.repo.ReceiptRepository;
import com.google.common.primitives.UnsignedInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private ReceiptRepository receiptRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, ReceiptRepository receiptRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.receiptRepository = receiptRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void addItem(Long id, Long itemId) {
        Order order = orderRepository.findOne(id);
        Item item = itemRepository.findOne(itemId);

        LineItem lineItem = new LineItem();
        lineItem.setItem(item);
        lineItem.setOrder(order);
        lineItem.setQuantity(1);
        lineItem.setCurrentPrice(item.getPrice());

        order.getLineItems().add(lineItem);

        orderRepository.save(order);
    }

    public Receipt pay(Long id, BigDecimal payment) {
        Order order = orderRepository.findOne(id);

        Receipt receipt = new Receipt();
        receipt.setPayment(payment);
        receipt.setOrder(order);

        if (receipt.getChange().compareTo(BigDecimal.ZERO) != -1) {
            order.setPaid(true);
        } else {
            throw new RuntimeException("Not enough money exception");
        }

        receiptRepository.save(receipt);

        return receipt;
    }
}
