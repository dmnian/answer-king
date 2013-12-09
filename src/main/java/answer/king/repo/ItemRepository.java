package answer.king.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import answer.king.model.Item;

public interface ItemRepository  extends PagingAndSortingRepository<Item, Long> {
}