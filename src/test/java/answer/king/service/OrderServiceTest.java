package answer.king.service;

import answer.king.model.Item;
import answer.king.model.LineItem;
import answer.king.model.Order;
import answer.king.model.Receipt;
import answer.king.repo.ItemRepository;
import answer.king.repo.OrderRepository;
import answer.king.repo.ReceiptRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;

    private OrderRepository orderRepositoryMock;
    private ItemRepository itemRepositoryMock;
    private ReceiptRepository receiptRepositoryMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        orderRepositoryMock = mock(OrderRepository.class);
        itemRepositoryMock = mock(ItemRepository.class);
        receiptRepositoryMock = mock(ReceiptRepository.class);

        orderService = new OrderService(orderRepositoryMock, itemRepositoryMock, receiptRepositoryMock);
    }

    @Test
    public void saveOrderExecutedTest() throws Exception {
        orderService.save(new Order());

        verify(orderRepositoryMock, times(1)).save(any(Order.class));
    }

    @Test
    public void addItemTest() throws Exception {
        Order order = new Order();

        ArrayList<LineItem> items = new ArrayList<>();
        order.setLineItems(items);

        Item item = new Item();
        item.setPrice(new BigDecimal("20.00"));

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);
        when(itemRepositoryMock.findOne(anyLong())).thenReturn(item);

        orderService.addItem(1L, 1L, 1);

        assertEquals(1, items.size());

        verify(orderRepositoryMock, times(1)).save(order);
    }

    @Test
    public void addManyItemsTest() throws Exception {
        Order order = new Order();

        ArrayList<LineItem> items = new ArrayList<>();
        order.setLineItems(items);

        Item item = new Item();
        item.setId(1L);
        item.setPrice(new BigDecimal("20.50"));

        Item item2 = new Item();
        item2.setId(2L);
        item2.setPrice(new BigDecimal("22.50"));

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);
        when(itemRepositoryMock.findOne(anyLong()))
                .thenReturn(item)
                .thenReturn(item)
                .thenReturn(item2);

        orderService.addItem(1L, 1L, 1);
        orderService.addItem(1L, 1L, 1);
        orderService.addItem(1L, 2L, 4);

        assertEquals(2, items.size());
        assertEquals(4, items.get(1).getQuantity().intValue());
        assertEquals(2, items.get(0).getQuantity().intValue());

        verify(orderRepositoryMock, times(3)).save(order);
    }

    @Test
    public void quantityShouldBeGreaterThanZeroException(){
        thrown.expect(IllegalArgumentException.class);

        orderService.addItem(1L, 1L, 0);
    }

    @Test
    public void payTest() throws Exception {
        Order order = new Order();
        order.setLineItems(new ArrayList<>());

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);

        Receipt payExpected = new Receipt();
        payExpected.setOrder(order);
        payExpected.setPayment(new BigDecimal("99.99"));

        Receipt payResult = orderService.pay(1L, new BigDecimal("99.99"));

        assertEquals(payExpected, payResult);
    }

    @Test
    public void shouldUpdatePaidFlagInOrder() throws Exception {
        Order order = new Order();

        LineItem lineItem = new LineItem();
        lineItem.setCurrentPrice(new BigDecimal("99.00"));
        order.setLineItems(Arrays.asList(lineItem));


        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);

        orderService.pay(1L, new BigDecimal("99.99"));

        assertTrue(order.getPaid());
    }

    @Test
    public void shouldThrowExceptionWhenNotEnoughPaid() throws Exception {
        thrown.expect(RuntimeException.class);
        Order order = new Order();

        LineItem lineItem = new LineItem();
        lineItem.setCurrentPrice(new BigDecimal("100.00"));
        order.setLineItems(Arrays.asList(lineItem));

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);

        orderService.pay(1L, new BigDecimal("1.00"));
    }


    @Test
    public void shouldSaveReceipt() throws Exception {
        Order order = new Order();

        LineItem lineItem = new LineItem();
        lineItem.setCurrentPrice(new BigDecimal("100.00"));
        order.setLineItems(Arrays.asList(lineItem));

        when(orderRepositoryMock.findOne(anyLong())).thenReturn(order);

        orderService.pay(1L, new BigDecimal("100.00"));

        verify(receiptRepositoryMock, times(1)).save(any(Receipt.class));
    }

    @Test
    public void shouldCalculateChangeWithRightPrice() throws Exception {

        Item item = new Item();
        item.setPrice(new BigDecimal("20.00"));

        LineItem lineItem = new LineItem();
        lineItem.setItem(item);
        lineItem.setCurrentPrice(new BigDecimal("22.00"));

        ArrayList<LineItem> lineItems = new ArrayList<>();
        lineItems.add(lineItem);

        Order order = new Order();
        order.setLineItems(lineItems);

        Receipt receipt = new Receipt();
        receipt.setOrder(order);
        receipt.setPayment(new BigDecimal("22.00"));

        assertEquals(new BigDecimal("0.00"), receipt.getChange());
    }
}