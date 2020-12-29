package ru.itis.homework3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.homework3.model.Address;
import ru.itis.homework3.model.Order;
import ru.itis.homework3.model.Product;
import ru.itis.homework3.model.Status;
import ru.itis.homework3.model.User;
import ru.itis.homework3.service.OrderService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class OrderTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        when(orderService.confirmOrder(1L)).thenReturn(confirmedOrder());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/order/1/addProduct/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(confirmedOrder().getStatus().toString()))
                .andDo(document("add_product", responseFields(
                        fieldWithPath("status").description("Статус заказа"),
                        subsectionWithPath("user").description("Пользователь"),
                        subsectionWithPath("products").description("Список продуктов"),
                        subsectionWithPath("address").description("Адресс доставки"),
                        subsectionWithPath("id").description("Идентификатор"),
                        subsectionWithPath("_links.confirm").description("Подтвердить"),
                        subsectionWithPath("_links.addProduct").description("Добавить продукт")
                )));
    }

    private Order confirmedOrder() {
        return Order.builder()
                .id(1L)
                .consumer(User.builder()
                        .id(1L)
                        .name("name")
                        .build())
                .products(Collections.singletonList(Product.builder()
                        .id(1L)
                        .name("name")
                        .price(140)
                        .build()))
                .status(Status.Created)
                .address(Address.builder()
                        .address("pushkina")
                        .city("kazan")
                        .build())
                .build();

    }

}
