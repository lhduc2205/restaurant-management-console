package com.lhduc.service.impl;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.common.filtered.PropertyFilter;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.OperationForbiddenException;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.dto.OrderDto;
import com.lhduc.model.entity.Order;
import com.lhduc.model.mapper.ModelMapper;
import com.lhduc.repository.OrderRepository;
import com.lhduc.repository.impl.OrderRepositoryImpl;
import com.lhduc.service.OrderDetailService;
import com.lhduc.service.OrderService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final PropertyFilter propertyFilter;
    private final ModelMapper mapper;

    public OrderServiceImpl() {
        this.orderRepository = new OrderRepositoryImpl();
        this.orderDetailService = new OrderDetailServiceImpl();
        this.propertyFilter = new PropertyFilter();
        this.mapper = new ModelMapper();
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

        Collections.sort(orders);
        return orders;
    }

    @Override
    public List<OrderDto> getAll(FilterCondition filterCondition) {
        List<OrderDto> orders = this.getAll();

        return propertyFilter.filter(orders, filterCondition);
    }

    /**
     * Retrieves an entity of type OrderDto by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified ID, or null if not found.
     */
    @Override
    public OrderDto getById(int id) {
        Order existedOrder = this.getExistedOrderById(id);

        OrderDto orderDto = mapper.map(existedOrder, OrderDto.class);
        orderDto.setOrderDetail(orderDetailService.getByOrderId(id));

        return orderDto;
    }

    /**
     * Creates a new entity of type OrderDto.
     *
     * @param orderDto The entity to create.
     */
    @Override
    public void create(OrderDto orderDto) {
        Order order = mapper.map(orderDto, Order.class);
        order.setCreatedAt(LocalDateTime.now());

        OrderDto createdOrderDto = mapper.map(orderRepository.createOrder(order), OrderDto.class);

        orderDetailService.create(orderDto.getOrderDetail(), createdOrderDto.getId());
    }

    /**
     * Updates an existing entity of type OrderDto.
     *
     * @param orderDto The entity to update.
     * @throws OperationForbiddenException If the status of order is PAID.
     */
    @Override
    public void update(OrderDto orderDto) {
        Order existedOrder = this.getExistedOrderById(orderDto.getId());

        if (existedOrder.getOrderStatus().isNotEditable()) {
            throw new OperationForbiddenException(MessageConstant.UNABLE_UPDATE_ORDER);
        }

        Order updatedOrder = mapper.map(orderDto, Order.class);
        orderRepository.update(updatedOrder);
    }

    /**
     * Deletes an entity of type OrderDto by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    @Override
    public void delete(int id) {
        this.getExistedOrderById(id);

        orderDetailService.deleteByOrderId(id);
        orderRepository.deleteById(id);
    }

    private Order getExistedOrderById(int id) {
        return orderRepository.getById(id).orElseThrow(() -> new NotFoundException("Order", id));
    }
}
