package com.lhduc.services.impl;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.models.dtos.OrderDetailDto;
import com.lhduc.models.dtos.OrderDto;
import com.lhduc.models.entities.Order;
import com.lhduc.models.mappers.ModelMapper;
import com.lhduc.repositories.OrderRepository;
import com.lhduc.repositories.impl.OrderRepositoryImpl;
import com.lhduc.services.OrderDetailService;
import com.lhduc.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ModelMapper mapper;

    public OrderServiceImpl() {
        this.orderRepository = ServiceLocator.getService(OrderRepositoryImpl.class.getName());
        this.orderDetailService = ServiceLocator.getService(OrderDetailServiceImpl.class.getName());
        this.mapper = ServiceLocator.getService(ModelMapper.class.getName());
    }

    /**
     * Retrieves a list of all entities of type OrderDto.
     *
     * @return A list of all entities.
     */
    @Override
    public List<OrderDto> getAll() {
        List<OrderDto> orders = mapper.mapList(orderRepository.getAll(), OrderDto.class);
        orders.forEach(order -> {
            List<OrderDetailDto> details = orderDetailService.getByOrderId(order.getId());
            order.setOrderDetail(details);
        });

        return orders;
    }

    /**
     * Retrieves an entity of type OrderDto by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified ID, or null if not found.
     */
    @Override
    public OrderDto getById(int id) {
        Order existedOrder = this.checkExistedOrderById(id);

        OrderDto orderDto = mapper.map(existedOrder, OrderDto.class);
        orderDto.setOrderDetail(orderDetailService.getByOrderId(id));

        return orderDto;
    }

    /**
     * Creates a new entity of type OrderDto.
     *
     * @param orderDto The entity to create.
     * @return The created entity.
     */
    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = mapper.map(orderDto, Order.class);
        return mapper.map(orderRepository.create(order), OrderDto.class);
    }

    /**
     * Updates an existing entity of type OrderDto.
     *
     * @param orderDto The entity to update.
     * @return The updated entity.
     */
    @Override
    public OrderDto update(OrderDto orderDto) {
        this.checkExistedOrderById(orderDto.getId());

        Order updatedOrder = mapper.map(orderDto, Order.class);
        return mapper.map(orderRepository.update(updatedOrder), OrderDto.class);
    }

    /**
     * Deletes an entity of type OrderDto by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    @Override
    public void deleteById(int id) {
        this.checkExistedOrderById(id);

        orderDetailService.deleteByOrderId(id);
        orderRepository.deleteById(id);
    }

    private Order checkExistedOrderById(int id) {
        return orderRepository.getById(id).orElseThrow(() -> new NotFoundException("Order", id));
    }
}
