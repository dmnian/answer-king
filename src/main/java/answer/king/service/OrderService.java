package answer.king.service;

import answer.king.model.Item;
import answer.king.model.Order;
import answer.king.model.Receipt;
import answer.king.repo.ItemRepository;
import answer.king.repo.OrderRepository;
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

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
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

        item.setOrder(order);
        order.getItems().add(item);

        orderRepository.save(order);
    }

    public Receipt pay(Long id, BigDecimal payment) {
        Order order = orderRepository.findOne(id);

        Receipt receipt = new Receipt();
        receipt.setPayment(payment);
        receipt.setOrder(order);
        return receipt;
    }
}
