package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.repository.OrderDetailRepository;
import com.lhduc.util.DatasourceUtil;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        return orderDetails.stream().filter(orderDetail -> orderDetail.getOrderId() == id).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetail> getById(int id) {
        throw new ApplicationRuntimeException("This function is not support");
    }

    @Override
    public Optional<OrderDetail> get(int orderId, int menuItemId) {
        List<OrderDetail> orderDetails = this.getAll();
        return orderDetails.stream()
                .filter(orderDetail -> orderDetail.getOrderId() == orderId && orderDetail.getMenuItemId() == menuItemId)
                .findFirst();
    }

    @Override
    public Optional<OrderDetail> create(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = this.getAll();
        orderDetails.add(orderDetail);

        this.save(orderDetails);

        return Optional.of(orderDetail);
    }

    @Override
    public Optional<OrderDetail> update(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = this.getAll();
        Optional<OrderDetail> existedOrderDetail = this.get(orderDetail.getOrderId(), orderDetail.getMenuItemId());

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
        throw new ApplicationRuntimeException("This function is not support");
    }

    @Override
    public void delete(int orderId, int menuItemId) {
        List<OrderDetail> orderDetails = this.getAll();
        orderDetails.removeIf(d -> d.getOrderId() == orderId && d.getMenuItemId() == menuItemId);

        this.save(orderDetails);
    }

    @Override
    public void deleteByOrderId(int id) {
        List<OrderDetail> orderDetails = this.getAll();
        orderDetails.removeIf(d -> d.getOrderId() == id);

        this.save(orderDetails);
    }

    private void save(List<OrderDetail> orderDetails) {
        this.datasource.saveAll(orderDetails, OrderDetail.class);
    }
}
