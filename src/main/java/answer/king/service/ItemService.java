package answer.king.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import answer.king.model.Item;
import answer.king.repo.ItemRepository;

import javax.validation.ConstraintViolationException;

@Service
@Transactional
public class ItemService {
	private Logger logger = LoggerFactory.getLogger(ItemService.class);
	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<Item> getAll() {
		return itemRepository.findAll();
	}

	public Item save(Item item) {
		if(item == null){
			throw new NullPointerException("item cannot be null");
		}

		Item saved = null;
		try {
			saved = itemRepository.save(item);
		} catch (ConstraintViolationException e) {
			logger.error("{}", e.getMessage());
		}

		return saved;
	}
}
