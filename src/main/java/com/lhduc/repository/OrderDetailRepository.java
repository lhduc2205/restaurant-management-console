package com.lhduc.repository;

import com.lhduc.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail> {
    public List<OrderDetail> getByOrderId(int id);
    public void deleteByOrderId(int orderId);
}
