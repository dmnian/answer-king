package answer.king.service;


import answer.king.model.Item;
import answer.king.repo.ItemRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        when(itemRepositoryMock.save(any(Item.class))).thenReturn(null);

        itemService.save(new Item());

        verify(itemRepositoryMock, times(1)).save(any(Item.class));
    }

    @Test
    public void itemShouldNotNullTest() throws Exception {
        thrown.expect(NullPointerException.class);
        itemService.save(null);
    }
}