package answer.king.controller;

import answer.king.model.Item;
import answer.king.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Item updatePrice(@PathVariable("id") Long id, @RequestBody BigDecimal price) {
        logger.info("updatedPrice: {}", price);
        Item item = itemService.updatePrice(id, price);

        return item;
    }
}
