package com.lhduc.service;

import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.model.dto.OrderDto;

import java.util.List;

public interface OrderService extends CrudService<OrderDto> {
    List<OrderDto> getAll(FilterCondition filterCondition);
}
