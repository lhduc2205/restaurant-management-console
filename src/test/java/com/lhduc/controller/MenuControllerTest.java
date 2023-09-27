package com.lhduc.controller;

import com.lhduc.common.enums.MenuCategory;
import com.lhduc.model.dto.MenuDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lhduc.service.MenuService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class MenuControllerTest {
    @Mock
    private static MenuService menuService;
    private static List<MenuDto> menus = new ArrayList<>();
    private static int testId;

    @BeforeEach
    public void setUp() {
        testId = 2;
        MenuDto menuDto1 = new MenuDto(1, MenuCategory.FOOD);
        MenuDto menuDto2 = new MenuDto(2, MenuCategory.DRINK);

        menus.add(menuDto1);
        menus.add(menuDto2);
        MockitoAnnotations.openMocks(this);

        when(menuService.getAll()).thenReturn(menus);
        when(menuService.getById(testId)).thenReturn(menuDto2);
    }

    @Test
    @DisplayName("Test getAll()")
    void testGetAll() {
        List<MenuDto> menuDtos = menuService.getAll();
        Assertions.assertEquals(menuDtos, menus);
    }

    @Test
    @DisplayName("Test getById(int id)")
    void testGetById() {
        MenuDto actualMenuDto = new MenuDto(2, MenuCategory.DRINK);

        MenuDto expectedMenuDto = menuService.getById(testId);

        Assertions.assertAll("Compare two objects",
                () -> Assertions.assertEquals(expectedMenuDto.getId(), actualMenuDto.getId()),
                () -> Assertions.assertEquals(expectedMenuDto.getCategory(), actualMenuDto.getCategory())
        );
    }
}