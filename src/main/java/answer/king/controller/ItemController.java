package answer.king.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import answer.king.model.Item;
import answer.king.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Item> getAll() {
		return itemService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Item create(@RequestBody Item item) {
		logger.info("trying to add item: {}", item);
		Item itemResult = itemService.save(item);
		logger.info("added item: {}", itemResult);

		return itemResult;
	}
}
