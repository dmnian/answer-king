package answer.king.model;

import java.math.BigDecimal;

public class Receipt {

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
		BigDecimal totalOrderPrice = order.getItems()
										  .stream()
										  .map(Item::getPrice)
										  .reduce(BigDecimal.ZERO, BigDecimal::add);

		return payment.subtract(totalOrderPrice);
	}
}
