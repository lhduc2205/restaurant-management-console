package com.lhduc.repository.impl;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.datasource.Datasource;
import com.lhduc.exception.NotFoundException;
import com.lhduc.model.entity.Order;
import com.lhduc.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderRepositoryImpl implements OrderRepository {
    private SortedSet<Order> orders = new TreeSet<>();
    private final Datasource datasource;

    public OrderRepositoryImpl() {
        datasource = ServiceLocator.getService(Datasource.class);
        this.getAll();
    }

    @Override
    public List<Order> getAll() {
        List<Order> ordersFromDb = this.datasource.readData(Order.class);
        this.orders = new TreeSet<>(ordersFromDb);
        return orders.stream().toList();
    }

    @Override
    public Optional<Order> getById(int id) throws NotFoundException {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    @Override
    public Optional<Order> create(Order order) {
        order.setId(this.generateId());
        this.orders.add(order);

        this.save();

        return Optional.of(order);
    }

    @Override
    public Optional<Order> update(Order order) {
        orders.removeIf(o -> o.getId() == order.getId());
        orders.add(order);

        this.save();

        return Optional.of(order);
    }

    @Override
    public void deleteById(int id) {
        this.orders.removeIf(o -> o.getId() == id);

        this.save();
    }

    public List<Order> getOrders() {
        return orders.stream().toList();
    }

    public void setOrders(List<Order> orders) {
        this.orders = new TreeSet<>(orders);
    }

    private void save() {
        this.datasource.saveAll(this.orders.stream().toList(), Order.class);
    }

    private int generateId() {
        return orders.isEmpty() ? 1 : orders.last().getId() + 1;
    }
}
