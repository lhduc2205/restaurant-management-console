package com.lhduc.repository.impl;

import com.lhduc.common.enums.MenuCategory;
import com.lhduc.datasource.Datasource;
import com.lhduc.model.entity.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuRepositoryImplTest {
    private static MenuRepositoryImpl menuRepository;
    @Mock
    private static Datasource datasource;
    private static final List<Menu> menus = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Menu menu1 = new Menu(1, MenuCategory.DRINK);
        Menu menu2 = new Menu(2, MenuCategory.FOOD);

        menus.add(menu1);
        menus.add(menu2);

        MockitoAnnotations.openMocks(this);

        menuRepository = new MenuRepositoryImpl(datasource);

        when(datasource.readData(Menu.class)).thenReturn(menus);
    }

    @AfterEach
    void tearDown() {
        menus.clear();
    }

    @Test
    @DisplayName("Test get all Menus")
    void getAll() {
        List<Menu> expectedMenus = menuRepository.getAll();

        assertEquals(menus.size(), expectedMenus.size());
        for (Menu expectedMenu : expectedMenus) {
            assertTrue(menus.contains(expectedMenu));
        }

        verify(datasource, times(1)).readData(Menu.class);
    }

    @Test
    @DisplayName("Test get Menu with id = 1")
    void getMenuById() {
        Menu expectedMenu = menus.get(0);

        Menu actualMenu = menuRepository.getById(1).orElse(null);

        assertNotNull(actualMenu);
        assertEquals(actualMenu, expectedMenu);
    }

    @Test
    @DisplayName("Test get nonexistent Menu with id = 1")
    void getNonexistentMenuById() {
        Optional<Menu> actualMenu = menuRepository.getById(999);
        assertTrue(actualMenu.isEmpty());
    }

    @Test
    @DisplayName("Test create new menu")
    void createNewMenu() {
        Menu expectedMenu = new Menu(3, MenuCategory.DRINK);

        menuRepository.create(expectedMenu);

        verify(datasource, times(1)).saveAll(menus, Menu.class);
    }

    @Test
    @DisplayName("Test update menu with id = 1")
    void updateMenu() {
        Menu expectedMenu = new Menu(1, MenuCategory.FOOD);
        menuRepository.update(expectedMenu);

        verify(datasource, times(1)).saveAll(menus, Menu.class);
    }

    @Test
    @DisplayName("Test update nonexistent menu with id = 999")
    public void updateNonexistentMenu() {
        Menu nonExistentMenu = new Menu(999, MenuCategory.DRINK);
        menuRepository.update(nonExistentMenu);

        verify(datasource, times(0)).saveAll(menus, Menu.class);
    }

    @Test
    @DisplayName("Test delete menu by id")
    void deleteMenuById() {
        assertTrue(menuRepository.getById(1).isPresent());

        menuRepository.deleteById(1);

        assertTrue(menuRepository.getById(1).isEmpty());

        verify(datasource, times(1)).saveAll(menus, Menu.class);
    }

    @Test
    @DisplayName("Test delete non-existent menu by id = 999")
    void deleteNonexistentMenuById() {
        assertTrue(menuRepository.getById(999).isEmpty());
        menuRepository.deleteById(999);
        assertTrue(menuRepository.getById(999).isEmpty());
    }
}