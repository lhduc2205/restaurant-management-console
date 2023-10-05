package com.lhduc.service.impl;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.OperationForbiddenException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.entity.MenuItem;
import com.lhduc.model.entity.Order;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.model.mapper.ModelMapper;
import com.lhduc.repository.MenuItemRepository;
import com.lhduc.repository.OrderDetailRepository;
import com.lhduc.repository.OrderRepository;
import com.lhduc.repository.impl.MenuItemRepositoryImpl;
import com.lhduc.repository.impl.OrderDetailRepositoryImpl;
import com.lhduc.repository.impl.OrderRepositoryImpl;
import com.lhduc.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;

    public OrderDetailServiceImpl() {
        this.orderDetailRepository = new OrderDetailRepositoryImpl();
        this.orderRepository = new OrderRepositoryImpl();
        this.menuItemRepository = new MenuItemRepositoryImpl();
        this.mapper = new ModelMapper();
    }

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.mapper = new ModelMapper();
    }

    /**
     * Retrieves a list of all entities of type OrderDetailDto.
     *
     * @return A list of all entities.
     */
    @Override
    public List<OrderDetailDto> getAll() {
        List<OrderDetailDto> ordersDetailDto = mapper.mapList(orderDetailRepository.getAll(), OrderDetailDto.class);
        this.populateMenuItemsDto(ordersDetailDto);

        return ordersDetailDto;
    }

    @Override
    public List<OrderDetailDto> getByOrderId(int orderId) {
        List<OrderDetailDto> ordersDetailDto = mapper.mapList(orderDetailRepository.getByOrderId(orderId), OrderDetailDto.class);
        this.populateMenuItemsDto(ordersDetailDto);

        return ordersDetailDto;
    }

    @Override
    public OrderDetailDto getById(int id) {
        return mapper.map(orderDetailRepository.getById(id), OrderDetailDto.class);
    }


    /**
     * Retrieves an OrderDetailDto object for a given order and menu item combination.
     * <p>
     * This method searches for an existing OrderDetail associated with the specified order ID
     * and menu item ID.
     *
     * @param orderId    The ID of the order to retrieve the order detail for.
     * @param menuItemId The ID of the menu item to retrieve the order detail for.
     * @return An OrderDetailDto representing the order detail for the specified order and menu item.
     * @throws NotFoundException If no OrderDetail is found for the provided order and menu item combination.
     */
    @Override
    public OrderDetailDto get(int orderId, int menuItemId) {
        OrderDetail orderDetail = this.checkExistedOrderDetail(orderId, menuItemId);

        return mapper.map(orderDetail, OrderDetailDto.class);
    }

    /**
     * Creates a new entity of type OrderDetailDto.
     *
     * @param orderDetailDto The entity to create.
     */
    @Override
    public void create(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = mapper.map(orderDetailDto, OrderDetail.class);
        orderDetail.setMenuItemId(orderDetailDto.getMenuItem().getId());

        orderDetailRepository.create(orderDetail);
    }

    @Override
    public List<OrderDetailDto> create(List<OrderDetailDto> orderDetailsDto, int orderId) {
        orderRepository.getById(orderId).orElseThrow(() -> new NotFoundException("Order"));

        List<OrderDetail> orderDetail = mapper.mapList(orderDetailsDto, OrderDetail.class);

        return mapper.mapList(orderDetailRepository.create(orderDetail, orderId), OrderDetailDto.class);
    }

    /**
     * Updates an existing entity of type OrderDetailDto.
     *
     * @param orderDetailDto The entity to update.
     * @throws OperationForbiddenException If the status of order is PAID.
     */
    @Override
    public void update(OrderDetailDto orderDetailDto) {
        Order existedOrder = orderRepository.getById(orderDetailDto.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order", orderDetailDto.getOrderId()));

        if (existedOrder.getOrderStatus().isNotEditable()) {
            throw new OperationForbiddenException(MessageConstant.UNABLE_UPDATE_ORDER);
        }

        this.checkExistedOrderDetail(orderDetailDto.getOrderId(), orderDetailDto.getMenuItemId());

        OrderDetail updatedOrderDetail = mapper.map(orderDetailDto, OrderDetail.class);
        orderDetailRepository.update(updatedOrderDetail);
    }

    /**
     * Deletes an entity of type OrderDetailDto by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    @Override
    public void delete(int id) {
    }

    @Override
    public void delete(int orderId, int menuItemId) {
        this.checkExistedOrderDetail(orderId, menuItemId);

        orderDetailRepository.delete(orderId, menuItemId);
    }

    @Override
    public void deleteByOrderId(int orderId) {
        orderDetailRepository.getById(orderId)
                .orElseThrow(() -> new NotFoundException("Order", orderId));

        orderDetailRepository.deleteByOrderId(orderId);
    }

    private void populateMenuItemsDto(List<OrderDetailDto> ordersDetailDto) {
        ordersDetailDto.forEach(detail -> detail.setMenuItem(getMenuItemDto(detail.getMenuItemId())));
    }

    private MenuItemDto getMenuItemDto(int menuItemId) {
        return mapper.map(menuItemRepository.getById(menuItemId).orElse(new MenuItem()), MenuItemDto.class);
    }

    private OrderDetail checkExistedOrderDetail(int orderId, int menuItemId) {
        return orderDetailRepository.get(orderId, menuItemId)
                .orElseThrow(() -> new NotFoundException("Order Detail with order id = " + orderId + " and menu item id = " + menuItemId + " was not found."));
    }

}
