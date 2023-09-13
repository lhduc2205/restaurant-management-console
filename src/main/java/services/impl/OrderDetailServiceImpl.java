package services.impl;

import common.patterns.servicelocator.ServiceLocator;
import exceptions.NotFoundException;
import models.dtos.MenuItemDto;
import models.dtos.OrderDetailDto;
import models.entities.MenuItem;
import models.entities.OrderDetail;
import models.mappers.ModelMapper;
import repositories.MenuItemRepository;
import repositories.OrderDetailRepository;
import repositories.impl.MenuItemRepositoryImpl;
import repositories.impl.OrderDetailRepositoryImpl;
import services.OrderDetailService;

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
        return null;
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
        orderDetailRepository.getById(orderDetailDto.getId())
                .orElseThrow(() -> new NotFoundException("Order detail's not found. Update failed"));

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
        orderDetailRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Order detail's not found. Delete failed"));

        orderDetailRepository.deleteById(id);
    }

    private void getMenuItemsDto(List<OrderDetailDto> ordersDetailDto) {
        ordersDetailDto.forEach(detail -> detail.setMenuItem(getMenuItemDto(detail.getMenuItemId())));
    }

    private MenuItemDto getMenuItemDto(int menuItemId) {
        return mapper.map(menuItemRepository.getById(menuItemId).orElse(new MenuItem()), MenuItemDto.class);
    }

}
