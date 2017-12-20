package answer.king.service;


import answer.king.model.Item;
import answer.king.repo.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private ItemService itemService;

    @Test
    public void name() throws Exception {
        Item entity = new Item();
        entity.setName("some item");

        entityManager.persist(entity);

//        List<Item> all = itemService.getAll();
//
//        System.out.println(all);
    }
}