package com.lhduc.service;

import com.lhduc.exception.NotFoundException;
import com.lhduc.model.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService extends CrudService<OrderDetailDto> {
    List<OrderDetailDto> getByOrderId(int orderId) throws NotFoundException;
    void deleteByOrderId(int orderId) throws NotFoundException;
}
