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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (payment != null ? !payment.equals(receipt.payment) : receipt.payment != null) return false;
        return order != null ? order.equals(receipt.order) : receipt.order == null;
    }

}
