package com.lhduc.service;

import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.model.dto.MenuDto;

import java.util.List;

/**
 * This interface defines the contract for handling business logic and interacting with menu repository.
 */
public interface MenuService extends CrudService<MenuDto> {
    public List<MenuDto> getAll(FilterCondition condition);
}
