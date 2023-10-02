package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.repository.OrderDetailRepository;
import com.lhduc.util.DatasourceUtil;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private final Datasource datasource;

    public OrderDetailRepositoryImpl() {
        this.datasource = DatasourceUtil.getDatasourceInstance();
    }

    public OrderDetailRepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<OrderDetail> getAll() {
        return this.datasource.readData(OrderDetail.class);
    }

    @Override
    public List<OrderDetail> getByOrderId(int id) {
        List<OrderDetail> orderDetails = this.getAll();
        return orderDetails.stream().filter(orderDetail -> orderDetail.getOrderId() == id).toList();
    }

    @Override
    public Optional<OrderDetail> getById(int id) {
        List<OrderDetail> orderDetails = this.getAll();
        return orderDetails.stream().filter(orderDetail -> orderDetail.getId() == id).findFirst();
    }

    @Override
    public Optional<OrderDetail> create(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = this.getAll();
        orderDetail.setId(this.generateId(orderDetails));
        orderDetails.add(orderDetail);

        this.save(orderDetails);

        return Optional.of(orderDetail);
    }

    @Override
    public Optional<OrderDetail> update(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = this.getAll();
        Optional<OrderDetail> existedOrderDetail = this.getById(orderDetail.getId());

        if (existedOrderDetail.isEmpty()) {
            return Optional.empty();
        }

        orderDetails.remove(existedOrderDetail.get());
        orderDetails.add(orderDetail);

        this.save(orderDetails);

        return Optional.of(orderDetail);
    }

    @Override
    public void deleteById(int id) {
        List<OrderDetail> orderDetails = this.getAll();
        orderDetails.removeIf(d -> d.getId() == id);

        this.save(orderDetails);
    }

    @Override
    public void deleteByOrderId(int id) {
        List<OrderDetail> orderDetails = this.getAll();
        orderDetails.removeIf(d -> d.getOrderId() == id);

        this.save(orderDetails);
    }

    private void save(List<OrderDetail> orderDetails) {
        this.datasource.saveAll(orderDetails.stream().toList(), OrderDetail.class);
    }

    private int generateId(List<OrderDetail> orderDetails) {
        return orderDetails.isEmpty() ? 1 : orderDetails.get(orderDetails.size() - 1).getId() + 1;
    }
}
