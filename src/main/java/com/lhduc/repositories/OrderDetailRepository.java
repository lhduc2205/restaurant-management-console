package com.lhduc.repositories;

import com.lhduc.models.entities.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail> {
    public List<OrderDetail> getByOrderId(int id);
    public void deleteByOrderId(int orderId);
}
