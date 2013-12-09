package answer.king.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import answer.king.model.Item;
import answer.king.model.Order;
import answer.king.model.Reciept;
import answer.king.repo.ItemRepository;
import answer.king.repo.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	public List<Order> getAll() {
		List<Order> orders = new ArrayList<>();
		for(Order order : orderRepository.findAll()) {
			orders.add(order);
		}
		return orders;
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

	public Reciept pay(Long id, BigDecimal payment) {
		Order order = orderRepository.findOne(id);

		Reciept reciept = new Reciept();
		reciept.setPayment(payment);
		reciept.setOrder(order);
		return reciept;
	}
}
