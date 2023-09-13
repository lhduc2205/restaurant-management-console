package controllers;

import common.patterns.servicelocator.ServiceLocator;
import models.dtos.OrderDto;
import services.OrderService;
import services.impl.OrderServiceImpl;

import java.util.List;

public class OrderController implements CrudController<OrderDto> {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = ServiceLocator.getService(OrderServiceImpl.class.getName());
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
