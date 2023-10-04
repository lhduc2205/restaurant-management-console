package com.lhduc.repository.impl;

import com.lhduc.datasource.Datasource;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.repository.OrderDetailRepository;
import com.lhduc.util.DatasourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void create(OrderDetail orderDetail) {
        List<OrderDetail> orderDetailsFromDb = this.getAll();
        Optional<OrderDetail> existedOrderDetail = this.getExistedOrderDetail(orderDetail, orderDetailsFromDb);

        if (existedOrderDetail.isPresent()) {
            this.combineQuantity(existedOrderDetail.get(), orderDetail.getQuantity(), orderDetailsFromDb);
            update(existedOrderDetail.get());
            return;
        }

        orderDetailsFromDb.add(orderDetail);

        this.save(orderDetailsFromDb);
    }

    @Override
    public List<OrderDetail> create(List<OrderDetail> orderDetails, int orderId) {
        List<OrderDetail> orderDetailsFromDb = this.getAll();

        for (OrderDetail orderDetail : orderDetails) {
            Optional<OrderDetail> existedOrderDetail = getExistedOrderDetail(orderDetail, orderDetailsFromDb);

            if (existedOrderDetail.isPresent()) {
                this.combineQuantity(orderDetail, existedOrderDetail.get().getQuantity(), orderDetailsFromDb);
            } else {
                orderDetail.setOrderId(orderId);
                orderDetailsFromDb.add(orderDetail);
            }
        }

        this.save(orderDetailsFromDb);

        return orderDetails;
    }

    private void combineQuantity(OrderDetail oldOrderDetail, int quantity, List<OrderDetail> orderDetails) {
        orderDetails.remove(oldOrderDetail);
        increaseQuantity(oldOrderDetail, quantity);
        orderDetails.add(oldOrderDetail);
    }

    private Optional<OrderDetail> getExistedOrderDetail(OrderDetail orderDetail, List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .filter(o -> o.getOrderId() == orderDetail.getOrderId() && o.getMenuItemId() == orderDetail.getMenuItemId())
                .findFirst();
    }

    private void increaseQuantity(OrderDetail existingDetail, int quantity) {
        int newQuantity = existingDetail.getQuantity() + quantity;
        existingDetail.setQuantity(newQuantity);
    }

    @Override
    public void update(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = this.getAll();
        Optional<OrderDetail> existedOrderDetail = this.get(orderDetail.getOrderId(), orderDetail.getMenuItemId());

        if (existedOrderDetail.isEmpty()) {
            return;
        }

        orderDetails.remove(existedOrderDetail.get());
        orderDetails.add(orderDetail);

        this.save(orderDetails);
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
