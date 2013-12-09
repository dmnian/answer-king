package answer.king.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import answer.king.model.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}