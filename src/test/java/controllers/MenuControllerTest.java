package controllers;

import cores.enums.MenuCategory;
import dtos.MenuDto;
import entities.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.MenuService;
import services.impl.MenuServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class MenuControllerTest {
    private static MenuService menuService;
    private static List<MenuDto> menus = new ArrayList<>();
    private static int idTest;

    @BeforeAll
    static void setUp() {
        idTest = 2;
        MenuDto menuDto1 = new MenuDto(1, MenuCategory.FOOD);
        MenuDto menuDto2 = new MenuDto(2, MenuCategory.DRINK);

        menus.add(menuDto1);
        menus.add(menuDto2);
        menuService = mock(MenuService.class);

        when(menuService.getAllMenu()).thenReturn(menus);
        when(menuService.getMenuById(idTest)).thenReturn(menuDto2);
    }

    @Test
    @DisplayName("Test getAll()")
    void testGetAll() {
        List<MenuDto> menuDtos = menuService.getAllMenu();
        Assertions.assertEquals(menuDtos, menus);
    }

    @Test
    @DisplayName("Test getById(int id)")
    void testGetById() {
        MenuDto actualMenuDto = new MenuDto(2, MenuCategory.DRINK);

        MenuDto expectedMenuDto = menuService.getMenuById(idTest);

        Assertions.assertAll("Compare two objects",
                () -> Assertions.assertEquals(expectedMenuDto.getId(), actualMenuDto.getId()),
                () -> Assertions.assertEquals(expectedMenuDto.getCategory(), actualMenuDto.getCategory())
        );
    }
}