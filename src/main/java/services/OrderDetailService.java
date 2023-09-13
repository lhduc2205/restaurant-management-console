package services;

import models.dtos.OrderDetailDto;

import java.util.List;

public interface OrderDetailService extends CrudService<OrderDetailDto> {
    List<OrderDetailDto> getByOrderId(int orderId);
}
