package com.lhduc.controller;

import com.lhduc.common.filtered.FilterCondition;
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

    public List<OrderDetailDto> getAll(FilterCondition filterCondition) {
        return orderDetailService.getAll(filterCondition);
    }

    @Override
    public OrderDetailDto getById(int id) {
        return orderDetailService.getById(id);
    }

    public OrderDetailDto get(int orderId, int menuItemId) {
        return orderDetailService.get(orderId, menuItemId);
    }

    @Override
    public void create(OrderDetailDto orderDetailDto) {
        orderDetailService.create(orderDetailDto);
    }

    @Override
    public void update(OrderDetailDto orderDetailDto) {
        orderDetailService.update(orderDetailDto);
    }

    @Override
    public void deleteById(int id) {
        orderDetailService.delete(id);
    }

    public void delete(int orderId, int menuItemId) {
        orderDetailService.delete(orderId, menuItemId);
    }
}
