package services.impl;

import common.patterns.servicelocator.ServiceLocator;
import exceptions.NotFoundException;
import models.dtos.OrderDetailDto;
import models.dtos.OrderDto;
import models.entities.Order;
import models.mappers.ModelMapper;
import repositories.OrderDetailRepository;
import repositories.OrderRepository;
import repositories.impl.OrderDetailRepositoryImpl;
import repositories.impl.OrderRepositoryImpl;
import services.OrderDetailService;
import services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDetailService orderDetailService;
    private final OrderRepository orderRepository;
    //    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapper mapper;

    public OrderServiceImpl() {
        this.orderDetailService = ServiceLocator.getService(OrderDetailServiceImpl.class.getName());
        this.orderRepository = ServiceLocator.getService(OrderRepositoryImpl.class.getName());
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
            order.setTotalPrice(calculateTotalPrice(details));
            order.setQuantity(details.size());
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
        Order existedOrder = orderRepository.getById(id).orElseThrow(() -> new NotFoundException("Does not exist order with id " + id));

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
        Order order = mapper.map(orderDto, Order.class);
        return mapper.map(orderRepository.update(order), OrderDto.class);
    }

    /**
     * Deletes an entity of type OrderDto by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    @Override
    public void deleteById(int id) {

    }

    private double calculateTotalPrice(List<OrderDetailDto> ordersDetail) {
        double totalPrice = 0;
        for (OrderDetailDto detail : ordersDetail) {
            totalPrice += detail.getMenuItem().getPrice();
        }

        return totalPrice;
    }
}