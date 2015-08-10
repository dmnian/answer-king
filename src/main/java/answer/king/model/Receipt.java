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
		BigDecimal totalOrderPrice = BigDecimal.ZERO;

		for (Item item : order.getItems()) {
			totalOrderPrice = totalOrderPrice.add(item.getPrice());
		}

		return payment.subtract(totalOrderPrice);
	}
}
