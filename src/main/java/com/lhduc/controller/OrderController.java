package com.lhduc.controller;

import com.lhduc.model.dto.OrderDto;
import com.lhduc.service.OrderService;
import com.lhduc.service.impl.OrderServiceImpl;

import java.util.List;

public class OrderController implements CrudController<OrderDto> {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @Override
    public OrderDto getById(int id) {
        return orderService.getById(id);
    }

    @Override
    public void create(OrderDto orderDto) {
        orderService.create(orderDto);
    }

    @Override
    public void update(OrderDto orderDto) {
        orderService.update(orderDto);
    }

    @Override
    public void deleteById(int id) {
        orderService.delete(id);
    }
}
