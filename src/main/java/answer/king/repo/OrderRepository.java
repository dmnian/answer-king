package answer.king.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import answer.king.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}