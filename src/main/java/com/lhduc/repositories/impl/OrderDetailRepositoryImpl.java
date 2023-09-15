package com.lhduc.repositories.impl;

import com.lhduc.common.patterns.servicelocator.ServiceLocator;
import com.lhduc.datasources.Datasource;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.exceptions.ResourceAlreadyExistsException;
import com.lhduc.models.entities.OrderDetail;
import com.lhduc.repositories.OrderDetailRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private Datasource datasource;
    private SortedSet<OrderDetail> orderDetails;

    public OrderDetailRepositoryImpl() {
        datasource = ServiceLocator.getService(Datasource.class.getName());
        this.getAll();
    }

    @Override
    public List<OrderDetail> getAll() {
        List<OrderDetail> orderDetailFromDb = this.datasource.readData(OrderDetail.class);
        this.orderDetails = new TreeSet<>(orderDetailFromDb);
        return orderDetails.stream().toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<OrderDetail> getByOrderId(int id) {
        return orderDetails.stream().filter(orderDetail -> orderDetail.getOrderId() == id).toList();
    }

    @Override
    public Optional<OrderDetail> getById(int id) throws NotFoundException {
        return orderDetails.stream().filter(orderDetail -> orderDetail.getId() == id).findFirst();
    }

    @Override
    public OrderDetail create(OrderDetail orderDetail) throws ResourceAlreadyExistsException {
        orderDetail.setId(this.generateId());
        this.orderDetails.add(orderDetail);

        this.save();

        return orderDetail;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) throws NotFoundException {
        OrderDetail existedOrderDetail = orderDetails.stream().filter(d -> d.getId() == orderDetail.getId()).findFirst().orElse(null);

        if (existedOrderDetail == null) {
            throw new NotFoundException("Order detail with id " + orderDetail.getId() + " does not exist");
        }

        orderDetails.remove(existedOrderDetail);
        orderDetails.add(orderDetail);

        this.save();

        return orderDetail;
    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        OrderDetail existedOrderDetail = orderDetails.stream().filter(d -> d.getId() == id).findFirst().orElse(null);

        if (existedOrderDetail == null) {
            throw new NotFoundException("Order detail with id " + id + " does not exist");
        }

        orderDetails.remove(existedOrderDetail);

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

}
