package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.OrderDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderDetailRepositoryImplTest {
    private OrderDetailRepositoryImpl orderDetailRepository;
    @Mock
    private Datasource datasource;

    private final List<OrderDetail> orderDetails = new ArrayList<>();

    @BeforeEach
    void setUp() {
        OrderDetail orderDetail1 = new OrderDetail(1, 1, 10, 2000);
        OrderDetail orderDetail2 = new OrderDetail(2, 1, 5, 10000);

        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);

        MockitoAnnotations.openMocks(this);

        orderDetailRepository = new OrderDetailRepositoryImpl(datasource);

        when(datasource.readData(OrderDetail.class)).thenReturn(orderDetails);
    }

    @AfterEach
    void tearDown() {
        orderDetails.clear();
    }

    @Test
    @DisplayName("Test get all Order Detail")
    void getAll() {
        List<OrderDetail> expectedOrderDetails = orderDetailRepository.getAll();

        assertEquals(orderDetails.size(), expectedOrderDetails.size());
        for (OrderDetail expectedOrderDetail : expectedOrderDetails) {
            assertTrue(orderDetails.contains(expectedOrderDetail));
        }

        verify(datasource, times(1)).readData(OrderDetail.class);
    }

    @Test
    @DisplayName("Test get all Order Detail by orderId = 1")
    void getByOrderId() {
        List<OrderDetail> actualOrderDetail = orderDetailRepository.getByOrderId(1);

        assertEquals(2, actualOrderDetail.size());
        for (OrderDetail detail : actualOrderDetail) {
            assertTrue(orderDetails.contains(detail));
        }
    }

    @Test
    @DisplayName("Test get Order Detail with order id = 1 and order item id = 2")
    void getOrderDetailById() {
        OrderDetail expectedOrderDetail = orderDetails.get(1);

        OrderDetail actualOrderDetail = orderDetailRepository.get(1, 2).orElse(null);

        assertNotNull(actualOrderDetail);
        assertEquals(actualOrderDetail, expectedOrderDetail);
    }

    @Test
    @DisplayName("Test get nonexistent Order Detail with order id = 999 and menu item id = 999")
    void getNonexistentOrderDetail() {
        Optional<OrderDetail> actualOrderDetail = orderDetailRepository.get(999, 999);
        assertTrue(actualOrderDetail.isEmpty());
    }

    @Test
    @DisplayName("Test create new Order Detail")
    void create() {
        OrderDetail expectedOrderDetail = new OrderDetail(3, 1);

        orderDetailRepository.create(expectedOrderDetail);

        verify(datasource, times(1)).saveAll(orderDetails, OrderDetail.class);
    }

    @Test
    @DisplayName("Test create existed Order Detail")
    void createExistedOrderDetail() {
        OrderDetail expectedOrderDetail = new OrderDetail(2, 1, 5);

        orderDetailRepository.create(expectedOrderDetail);

        verify(datasource, times(1)).saveAll(orderDetails, OrderDetail.class);
    }


    @Test
    @DisplayName("Test create list existed Order Detail")
    void createListExistedOrderDetail() {
        List<OrderDetail> orderDetailsInput = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail(1, 1, 2, 2000);
        OrderDetail orderDetail2 = new OrderDetail(2, 1, 3, 10000);
        orderDetailsInput.add(orderDetail1);
        orderDetailsInput.add(orderDetail2);

        List<OrderDetail> actualOrderDetail = orderDetailRepository.create(orderDetailsInput, 1);

        assertEquals(orderDetailsInput.size(), orderDetails.size());
        assertEquals(actualOrderDetail.get(0).getQuantity(), 12);
        assertEquals(actualOrderDetail.get(1).getQuantity(), 8);

        verify(datasource, times(1)).saveAll(orderDetails, OrderDetail.class);
    }

    @Test
    @DisplayName("Test update OrderDetail with order id = 3 and menu item id = 3")
    void updateOrderDetail() {
        OrderDetail expectedOrderDetail = new OrderDetail(2, 1, 5 , 2000);
        orderDetailRepository.update(expectedOrderDetail);

        verify(datasource, times(1)).saveAll(orderDetails, OrderDetail.class);
    }

    @Test
    @DisplayName("Test update nonexistent OrderDetail with id = 999")
    void updateNonexistentOrderDetail() {
        OrderDetail expectedOrderDetail = new OrderDetail(3, 3);
        orderDetailRepository.update(expectedOrderDetail);

        verify(datasource, times(0)).saveAll(orderDetails, OrderDetail.class);
    }

    @Test
    @DisplayName("Test delete OrderDetail with order id = 1 and menu item id = 1")
    void deleteByOrderIdAndMenuItemId() {
        assertTrue(orderDetailRepository.get(1, 1).isPresent());
        orderDetailRepository.delete(1, 1);
        assertTrue(orderDetailRepository.get(1, 1).isEmpty());

        verify(datasource, times(1)).saveAll(orderDetails, OrderDetail.class);
    }

    @Test
    @DisplayName("Test delete OrderDetail with orderId = 1")
    void deleteByOrderId() {
        List<OrderDetail> existedOrderDetails = orderDetailRepository.getByOrderId(1);
        List<OrderDetail> expectedOrderDetails = orderDetailRepository.getByOrderId(1);

        for (OrderDetail existedDetail : existedOrderDetails) {
            assertTrue(expectedOrderDetails.contains(existedDetail));
        }

        orderDetailRepository.deleteByOrderId(1);
        expectedOrderDetails = orderDetailRepository.getByOrderId(1);

        for (OrderDetail existedDetail : existedOrderDetails) {
            assertFalse(expectedOrderDetails.contains(existedDetail));
        }

        verify(datasource, times(1)).saveAll(orderDetails, OrderDetail.class);
    }
}