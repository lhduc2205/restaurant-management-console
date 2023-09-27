package com.lhduc.controller;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.service.OrderDetailService;
import com.lhduc.service.impl.OrderDetailServiceImpl;

import java.util.List;

public class OrderDetailController implements CrudController<OrderDetailDto> {
    private final OrderDetailService orderDetailService;

    public OrderDetailController() {
        this.orderDetailService = ServiceLocator.getService(OrderDetailServiceImpl.class.getName());
    }

    @Override
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll();
    }

    @Override
    public OrderDetailDto getById(int id) {
        return orderDetailService.getById(id);
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
        orderDetailService.deleteById(id);
    }
}
