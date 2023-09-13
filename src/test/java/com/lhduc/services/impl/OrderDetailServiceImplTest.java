package com.lhduc.services.impl;

import com.lhduc.common.enums.Origin;
import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.models.dtos.MenuItemDto;
import com.lhduc.models.dtos.OrderDetailDto;
import com.lhduc.models.entities.MenuItem;
import com.lhduc.models.entities.OrderDetail;
import com.lhduc.models.mappers.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lhduc.repositories.MenuItemRepository;
import com.lhduc.repositories.OrderDetailRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderDetailServiceImplTest {
    private static OrderDetailRepository orderDetailRepository;
    private static MenuItemRepository menuItemRepository;
    private static ModelMapper mapper;
    private static List<OrderDetail> orderDetails = new ArrayList<>(Arrays.asList(
            new OrderDetail(1, 1, 1),
            new OrderDetail(2, 2, 1)
    ));
    private static List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(
            new MenuItem(1, "Com tron", "Ngon", 35000, Origin.VIETNAMESE, 1),
            new MenuItem(2, "Com ga", "khong ngon", 40000, Origin.VIETNAMESE, 2)
    ));

    @BeforeEach
    void setUp() {
        orderDetailRepository = mock(OrderDetailRepository.class);
        menuItemRepository = mock(MenuItemRepository.class);
        mapper = ServiceLocator.getService(ModelMapper.class.getName());

        when(orderDetailRepository.getAll()).thenReturn(orderDetails);
        when(menuItemRepository.getAll()).thenReturn(menuItems);
    }

    @Test
    @DisplayName("Test get all method")
    void getAll() {
        List<OrderDetailDto> expectedResult = new ArrayList<>(Arrays.asList(
                new OrderDetailDto(1, 1, mapper.map(menuItems.get(0), MenuItemDto.class)),
                new OrderDetailDto(1, 2, mapper.map(menuItems.get(1), MenuItemDto.class))
        ));

        List<OrderDetailDto> actualResult = mapper.mapList(orderDetailRepository.getAll(), OrderDetailDto.class);
        for (int i = 0; i< actualResult.size(); i++) {
            actualResult.get(i).setMenuItem(mapper.map(menuItems.get(i), MenuItemDto.class));
        }


        for (int i = 0; i < expectedResult.size(); i++) {
            OrderDetailDto expected = expectedResult.get(i);
            OrderDetailDto actual = actualResult.get(i);
             assertEquals(expected.getOrderId(), actual.getOrderId());
             assertEquals(expected.getMenuItemId(), actual.getMenuItemId());
             assertEquals(expected.getMenuItem().getMenuId(), actual.getMenuItem().getMenuId());
             assertEquals(expected.getMenuItem().getPrice(), actual.getMenuItem().getPrice());
             assertEquals(expected.getMenuItem().getOrigin(), actual.getMenuItem().getOrigin());
             assertEquals(expected.getMenuItem().getName(), actual.getMenuItem().getName());
             assertEquals(expected.getMenuItem().getDescription(), actual.getMenuItem().getDescription());

        }
    }

    @Test
    void getByOrderId() {
    }

    @Test
    void getById() {
    }
}