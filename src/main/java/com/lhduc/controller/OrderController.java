package com.lhduc.controller;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.model.dto.OrderDto;
import com.lhduc.service.OrderService;
import com.lhduc.service.impl.OrderServiceImpl;

import java.util.List;

public class OrderController implements CrudController<OrderDto> {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = ServiceLocator.getService(OrderServiceImpl.class);
    }

    /**
     * @return
     */
    @Override
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public OrderDto getById(int id) {
        return orderService.getById(id);
    }

    /**
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto create(OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    /**
     * @param orderDto
     */
    @Override
    public OrderDto update(OrderDto orderDto) {
        return orderService.update(orderDto);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(int id) {
        orderService.deleteById(id);
    }
}
