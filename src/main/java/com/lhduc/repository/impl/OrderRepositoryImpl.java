package com.lhduc.repository.impl;

import com.lhduc.common.constant.AppConstant;
import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.Order;
import com.lhduc.repository.OrderRepository;
import com.lhduc.util.DatasourceUtil;
import com.lhduc.util.IdGenerator;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final Datasource datasource;
    private final IdGenerator idGenerator;

    public OrderRepositoryImpl() {
        datasource = DatasourceUtil.getDatasourceInstance();
        idGenerator = new IdGenerator(AppConstant.ORDER_ID_TXT_FILE);
    }

    @Override
    public List<Order> getAll() {
        return this.datasource.readData(Order.class);
    }

    @Override
    public Optional<Order> getById(int id) {
        List<Order> orders = this.getAll();
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    @Override
    public void create(Order order) {
        List<Order> orders = this.getAll();
        Order createdOrder = new Order(order);

        this.generateId(createdOrder);
        orders.add(createdOrder);

        this.save(orders);
    }

    @Override
    public Order createOrder(Order order) {
        List<Order> orders = this.getAll();
        Order createdOrder = new Order(order);

        this.generateId(createdOrder);
        orders.add(createdOrder);

        this.save(orders);
        return createdOrder;
    }

    @Override
    public void update(Order order) {
        List<Order> orders = this.getAll();
        orders.removeIf(o -> o.getId() == order.getId());
        orders.add(order);

        this.save(orders);
    }

    @Override
    public void deleteById(int id) {
        List<Order> orders = this.getAll();
        orders.removeIf(o -> o.getId() == id);

        this.save(orders);
    }

    private void save(List<Order> orders) {
        this.datasource.saveAll(orders, Order.class);
    }

    private void generateId(Order order) {
        order.setId(idGenerator.getGeneratedId());
    }
}
