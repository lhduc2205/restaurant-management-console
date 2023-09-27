package com.lhduc.repository.impl;

import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.repository.OrderDetailRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private final Datasource datasource;
    private SortedSet<OrderDetail> orderDetails;

    public OrderDetailRepositoryImpl() {
        this.datasource = ServiceLocator.getService(Datasource.class.getName());
        this.getAll();
    }

    public OrderDetailRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
        this.getAll();
    }

    @Override
    public List<OrderDetail> getAll() {
        List<OrderDetail> orderDetailFromDb = this.datasource.readData(OrderDetail.class);
        this.orderDetails = new TreeSet<>(orderDetailFromDb);
        return orderDetails.stream().toList();
    }

    @Override
    public List<OrderDetail> getByOrderId(int id) {
        return orderDetails.stream().filter(orderDetail -> orderDetail.getOrderId() == id).toList();
    }

    @Override
    public Optional<OrderDetail> getById(int id) {
        return orderDetails.stream().filter(orderDetail -> orderDetail.getId() == id).findFirst();
    }

    @Override
    public Optional<OrderDetail> create(OrderDetail orderDetail) {
        orderDetail.setId(this.generateId());
        this.orderDetails.add(orderDetail);

        this.save();

        return Optional.of(orderDetail);
    }

    @Override
    public Optional<OrderDetail> update(OrderDetail orderDetail) {
        Optional<OrderDetail> existedOrderDetail = this.getById(orderDetail.getId());

        if (existedOrderDetail.isEmpty()) {
            return Optional.empty();
        }

        orderDetails.remove(existedOrderDetail.get());
        orderDetails.add(orderDetail);

        this.save();

        return Optional.of(orderDetail);
    }

    @Override
    public void deleteById(int id) {
        orderDetails.removeIf(d -> d.getId() == id);

        this.save();
    }

    @Override
    public void deleteByOrderId(int id) {
        orderDetails.removeIf(d -> d.getOrderId() == id);

        this.save();
    }

    private void save() {
        this.datasource.saveAll(this.orderDetails.stream().toList(), OrderDetail.class);
    }

    private int generateId() {
        return orderDetails.isEmpty() ? 1 : orderDetails.last().getId() + 1;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails.stream().toList();
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = new TreeSet<>(orderDetails);
    }
}
