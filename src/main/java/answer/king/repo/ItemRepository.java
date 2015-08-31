package answer.king.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import answer.king.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}