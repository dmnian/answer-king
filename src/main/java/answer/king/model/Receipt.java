package answer.king.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "T_RECEIPT")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(precision = 7, scale = 2)
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
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
