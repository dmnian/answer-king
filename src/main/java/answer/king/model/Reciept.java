package answer.king.model;

import java.math.BigDecimal;
import java.util.List;

public class Reciept {

	private BigDecimal payment;

	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public BigDecimal getChange() {
		List<Item> items = order.getItems();
		BigDecimal totalOrderPrice = BigDecimal.ZERO;
		for(Item item : items) {
			totalOrderPrice = totalOrderPrice.add(item.getPrice());
		}

		BigDecimal change = payment.subtract(totalOrderPrice);

		return change;
	}
}
