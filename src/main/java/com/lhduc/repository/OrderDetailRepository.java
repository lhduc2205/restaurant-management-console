package com.lhduc.repository;

import com.lhduc.model.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends CrudRepository<OrderDetail> {
    Optional<OrderDetail> get(int orderId, int menuItemId);

    List<OrderDetail> getByOrderId(int id);

    void deleteByOrderId(int orderId);

    void delete(int orderId, int menuItemId);
}
