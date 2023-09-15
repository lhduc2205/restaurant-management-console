package com.lhduc.services;

import com.lhduc.exceptions.NotFoundException;
import com.lhduc.models.dtos.OrderDetailDto;

import java.util.List;

public interface OrderDetailService extends CrudService<OrderDetailDto> {
    List<OrderDetailDto> getByOrderId(int orderId) throws NotFoundException;
    void deleteByOrderId(int orderId) throws NotFoundException;
}
