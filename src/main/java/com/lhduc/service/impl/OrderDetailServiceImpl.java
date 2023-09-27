package com.lhduc.service.impl;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.exception.NotFoundException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.entity.MenuItem;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.model.mapper.ModelMapper;
import com.lhduc.repository.MenuItemRepository;
import com.lhduc.repository.OrderDetailRepository;
import com.lhduc.repository.impl.MenuItemRepositoryImpl;
import com.lhduc.repository.impl.OrderDetailRepositoryImpl;
import com.lhduc.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;

    public OrderDetailServiceImpl() {
        this.orderDetailRepository = ServiceLocator.getService(OrderDetailRepositoryImpl.class.getName());
        this.menuItemRepository = ServiceLocator.getService(MenuItemRepositoryImpl.class.getName());
        this.mapper = ServiceLocator.getService(ModelMapper.class.getName());
    }

    /**
     * Retrieves a list of all entities of type OrderDetailDto.
     *
     * @return A list of all entities.
     */
    @Override
    public List<OrderDetailDto> getAll() {
        List<OrderDetailDto> ordersDetailDto = mapper.mapList(orderDetailRepository.getAll(), OrderDetailDto.class);
        this.getMenuItemsDto(ordersDetailDto);

        return ordersDetailDto;
    }

    @Override
    public List<OrderDetailDto> getByOrderId(int orderId) {
        List<OrderDetailDto> ordersDetailDto = mapper.mapList(orderDetailRepository.getByOrderId(orderId), OrderDetailDto.class);
        this.getMenuItemsDto(ordersDetailDto);

        return ordersDetailDto;
    }

    /**
     * Retrieves an entity of type OrderDetailDto by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified ID, or null if not found.
     */
    @Override
    public OrderDetailDto getById(int id) {
        OrderDetail orderDetail = this.checkExistedOrderDetailById(id);

        return mapper.map(orderDetail, OrderDetailDto.class);
    }

    /**
     * Creates a new entity of type OrderDetailDto.
     *
     * @param orderDetailDto The entity to create.
     * @return The created entity.
     */
    @Override
    public OrderDetailDto create(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = mapper.map(orderDetailDto, OrderDetail.class);
        orderDetail.setMenuItemId(orderDetailDto.getMenuItem().getId());

        return mapper.map(orderDetailRepository.create(orderDetail), OrderDetailDto.class);
    }

    /**
     * Updates an existing entity of type OrderDetailDto.
     *
     * @param orderDetailDto The entity to update.
     * @return The updated entity.
     */
    @Override
    public OrderDetailDto update(OrderDetailDto orderDetailDto) {
        this.checkExistedOrderDetailById(orderDetailDto.getId());

        OrderDetail updatedOrderDetail = mapper.map(orderDetailDto, OrderDetail.class);
        return mapper.map(orderDetailRepository.update(updatedOrderDetail), OrderDetailDto.class);
    }

    /**
     * Deletes an entity of type OrderDetailDto by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    @Override
    public void deleteById(int id) {
        this.checkExistedOrderDetailById(id);

        orderDetailRepository.deleteById(id);
    }

    @Override
    public void deleteByOrderId(int orderId) {
        orderDetailRepository.getById(orderId)
                .orElseThrow(() -> new NotFoundException("Order", orderId));

        orderDetailRepository.deleteByOrderId(orderId);
    }

    private void getMenuItemsDto(List<OrderDetailDto> ordersDetailDto) {
        ordersDetailDto.forEach(detail -> detail.setMenuItem(getMenuItemDto(detail.getMenuItemId())));
    }

    private MenuItemDto getMenuItemDto(int menuItemId) {
        return mapper.map(menuItemRepository.getById(menuItemId).orElse(new MenuItem()), MenuItemDto.class);
    }

    private OrderDetail checkExistedOrderDetailById(int id) {
        return orderDetailRepository.getById(id).orElseThrow(() -> new NotFoundException("Order Detail", id));
    }

}
