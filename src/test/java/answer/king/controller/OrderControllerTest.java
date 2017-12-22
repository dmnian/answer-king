package answer.king.controller;

import answer.king.model.Order;
import answer.king.model.Receipt;
import answer.king.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTest() throws Exception {
        Order order = new Order();
        order.setId(1L);

        given(orderServiceMock.save(any(Order.class))).willReturn(order);

        this.mockMvc.perform(post("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void addItemTest() throws Exception {

        this.mockMvc.perform(put("/order/1/addItem/1"))
                .andExpect(status().isOk());

        verify(orderServiceMock, times(1)).addItem(1L, 1L);
    }

    @Test
    public void payTest() throws Exception {
        BigDecimal payment = new BigDecimal("99.99");
        Receipt t = new Receipt();
        Order order = new Order();
        order.setItems(new ArrayList<>());
        t.setOrder(order);
        t.setPayment(payment);


        given(orderServiceMock.pay(any(), any())).willReturn(t);

        String content = objectMapper.writeValueAsString(payment);
        this.mockMvc.perform(put("/order/1/pay")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payment", is(99.99)));
    }
}