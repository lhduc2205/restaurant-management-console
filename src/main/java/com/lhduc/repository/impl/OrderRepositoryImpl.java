package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.Order;
import com.lhduc.repository.OrderRepository;
import com.lhduc.util.DatasourceUtil;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final Datasource datasource;

    public OrderRepositoryImpl() {
        datasource = DatasourceUtil.getDatasourceInstance();
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
    public Optional<Order> create(Order order) {
        List<Order> orders = this.getAll();
        order.setId(this.generateId(orders));
        orders.add(order);

        this.save(orders);

        return Optional.of(order);
    }

    @Override
    public Optional<Order> update(Order order) {
        List<Order> orders = this.getAll();
        orders.removeIf(o -> o.getId() == order.getId());
        orders.add(order);

        this.save(orders);

        return Optional.of(order);
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

    private int generateId(List<Order> orders) {
        return orders.isEmpty() ? 1 : orders.get(orders.size() - 1).getId() + 1;
    }
}
