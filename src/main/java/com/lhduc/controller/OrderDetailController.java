package com.lhduc.controller;

import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.service.OrderDetailService;
import com.lhduc.service.impl.OrderDetailServiceImpl;

import java.util.List;

public class OrderDetailController implements CrudController<OrderDetailDto> {
    private final OrderDetailService orderDetailService;

    public OrderDetailController() {
        this.orderDetailService = new OrderDetailServiceImpl();
    }

    @Override
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll();
    }

    @Override
    public OrderDetailDto getById(int id) {
        return orderDetailService.getById(id);
    }

    public OrderDetailDto get(int orderId, int menuItemId) {
        return orderDetailService.get(orderId, menuItemId);
    }

    @Override
    public OrderDetailDto create(OrderDetailDto orderDetailDto) {
        return orderDetailService.create(orderDetailDto);
    }

    @Override
    public OrderDetailDto update(OrderDetailDto orderDetailDto) {
        return orderDetailService.update(orderDetailDto);
    }

    @Override
    public void deleteById(int id) {
        orderDetailService.delete(id);
    }

    public void delete(int orderId, int menuItemId) {
        orderDetailService.delete(orderId, menuItemId);
    }
}
