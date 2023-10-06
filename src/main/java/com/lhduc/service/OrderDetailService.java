package com.lhduc.service;

import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.exception.NotFoundException;
import com.lhduc.model.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService extends CrudService<OrderDetailDto> {
    List<OrderDetailDto> getAll(FilterCondition filterCondition);
    OrderDetailDto get(int orderId, int menuItemId);
    List<OrderDetailDto> getByOrderId(int orderId) throws NotFoundException;
    void create(List<OrderDetailDto> orderDetailsDto, int orderId);
    void deleteByOrderId(int orderId) throws NotFoundException;
    void delete(int orderId, int menuItemId);
}
