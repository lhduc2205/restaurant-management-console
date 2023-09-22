package com.lhduc.repositories.impl;

import com.lhduc.datasources.Datasource;
import com.lhduc.models.entities.Menu;
import com.lhduc.models.entities.Order;
import com.lhduc.models.entities.OrderDetail;
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

    private static final List<OrderDetail> orderDetails = new ArrayList<>();

    @BeforeEach
    void setUp() {
        OrderDetail orderDetail1 = new OrderDetail(1, 1, 1);
        OrderDetail orderDetail2 = new OrderDetail(2, 2, 1);

        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);

        MockitoAnnotations.openMocks(this);

        orderDetailRepository = new OrderDetailRepositoryImpl(datasource);
        orderDetailRepository.setOrderDetails(orderDetails);

        when(datasource.readData(OrderDetail.class)).thenReturn(orderDetailRepository.getOrderDetails());
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

        verify(datasource, times(2)).readData(OrderDetail.class);
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
    @DisplayName("Test get Order Detail with id = 1")
    void getOrderDetailById() {
        OrderDetail expectedOrderDetail = orderDetails.get(0);

        OrderDetail actualOrderDetail = orderDetailRepository.getById(1).orElse(null);

        assertNotNull(actualOrderDetail);
        assertEquals(actualOrderDetail, expectedOrderDetail);
    }

    @Test
    @DisplayName("Test get nonexistent Order Detail with id = 999")
    void getNonexistentOrderDetailById() {
        Optional<OrderDetail> actualOrderDetail = orderDetailRepository.getById(999);
        assertTrue(actualOrderDetail.isEmpty());
    }

    @Test
    @DisplayName("Test create new Order Detail")
    void create() {
        OrderDetail expectedOrderDetail = new OrderDetail(3, 3, 1);

        Optional<OrderDetail> actualOrderDetail = orderDetailRepository.create(expectedOrderDetail);

        assertTrue(actualOrderDetail.isPresent());
        assertEquals(actualOrderDetail.get(), expectedOrderDetail);

        verify(datasource, times(1)).saveAll(orderDetailRepository.getOrderDetails(), OrderDetail.class);
    }

    @Test
    @DisplayName("Test update OrderDetail with id = 1")
    void updateOrderDetail() {
        OrderDetail expectedOrderDetail = new OrderDetail(1, 3, 3);
        Optional<OrderDetail> actualOrderDetail = orderDetailRepository.update(expectedOrderDetail);

        assertTrue(actualOrderDetail.isPresent());
        assertEquals(actualOrderDetail.get(), expectedOrderDetail);

        verify(datasource, times(1)).saveAll(orderDetailRepository.getOrderDetails(), OrderDetail.class);
    }

    @Test
    @DisplayName("Test update nonexistent OrderDetail with id = 999")
    void updateNonexistentOrderDetail() {
        OrderDetail expectedOrderDetail = new OrderDetail(999, 3, 3);
        Optional<OrderDetail> actualOrderDetail = orderDetailRepository.update(expectedOrderDetail);

        assertTrue(actualOrderDetail.isEmpty());

        verify(datasource, times(0)).saveAll(orderDetailRepository.getOrderDetails(), OrderDetail.class);
    }

    @Test
    @DisplayName("Test delete OrderDetail with id = 999")
    void deleteById() {
        assertTrue(orderDetailRepository.getById(1).isPresent());
        orderDetailRepository.deleteById(1);
        assertTrue(orderDetailRepository.getById(1).isEmpty());

        verify(datasource, times(1)).saveAll(orderDetailRepository.getOrderDetails(), OrderDetail.class);
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

        verify(datasource, times(1)).saveAll(orderDetailRepository.getOrderDetails(), OrderDetail.class);
    }
}