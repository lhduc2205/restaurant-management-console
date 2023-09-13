package repositories;

import models.entities.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail> {
    public List<OrderDetail> getByOrderId(int id);
}
