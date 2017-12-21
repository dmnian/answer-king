package answer.king.service;

import answer.king.model.Item;
import answer.king.model.Order;
import answer.king.model.Receipt;
import answer.king.repo.ItemRepository;
import answer.king.repo.OrderRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;

    private OrderRepository orderRepositoryMock;
    private ItemRepository itemRepositoryMock;

    @Before
    public void setUp() throws Exception {
        orderRepositoryMock = mock(OrderRepository.class);
        itemRepositoryMock = mock(ItemRepository.class);

        orderService = new OrderService(orderRepositoryMock, itemRepositoryMock);
    }

    @Test
    public void saveOrderExecutedTest() throws Exception {
        orderService.save(new Order());

        verify(orderRepositoryMock, times(1)).save(any(Order.class));
    }

    @Test
    public void addItemTest() throws Exception {
        Order order = new Order();

        ArrayList<Item> items = new ArrayList<>();
        order.setItems(items);

        Item item = new Item();

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);
        when(itemRepositoryMock.findOne(anyLong())).thenReturn(item);

        orderService.addItem(1L, 1L);

        assertEquals(order, item.getOrder());
        assertEquals(1, items.size());

        verify(orderRepositoryMock, times(1)).save(order);
    }

    @Test
    public void payTest() throws Exception {
        Order order = new Order();

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);

        Receipt payExpected = new Receipt();
        payExpected.setOrder(order);
        payExpected.setPayment(new BigDecimal("99.99"));

        Receipt payResult = orderService.pay(1L, new BigDecimal("99.99"));

        assertEquals(payExpected, payResult);
    }
}