package answer.king.service;


import answer.king.model.Item;
import answer.king.repo.ItemRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class ItemServiceTest {

    private ItemService itemService;
    private ItemRepository itemRepositoryMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        itemRepositoryMock = mock(ItemRepository.class);

        itemService = new ItemService(itemRepositoryMock);
    }

    @Test
    public void saveExecutedTest() throws Exception {

        itemService.save(new Item());

        verify(itemRepositoryMock, times(1)).save(any(Item.class));
    }

    @Test
    public void itemShouldNotNullTest() throws Exception {
        thrown.expect(NullPointerException.class);
        itemService.save(null);
    }

    @Test
    public void shouldUpdatePrice() throws Exception {
        BigDecimal updatedPrice = new BigDecimal("10.00");
        given(itemRepositoryMock.findOne(anyLong())).willReturn(new Item());

        Item item = itemService.updatePrice(1L, updatedPrice);

        verify(itemRepositoryMock, times(1)).findOne(anyLong());

        assertEquals(updatedPrice, item.getPrice());
    }

    @Test
    public void shouldThrowExceptionWhenItemNotExist() throws Exception {
        thrown.expect(RuntimeException.class);

        Item item = itemService.updatePrice(2L, new BigDecimal("10.00"));
    }


}