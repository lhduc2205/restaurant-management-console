package com.lhduc.repositories.impl;

import com.lhduc.common.enums.MenuCategory;
import com.lhduc.common.enums.Origin;
import com.lhduc.datasources.Datasource;
import com.lhduc.exceptions.NotFoundException;
import com.lhduc.exceptions.ResourceAlreadyExistsException;
import com.lhduc.models.entities.Menu;
import com.lhduc.models.entities.MenuItem;
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

class MenuItemRepositoryImplTest {
    private MenuItemRepositoryImpl menuItemRepository;
    @Mock
    private Datasource datasource;
    private final List<MenuItem> menuItems = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MenuItem menuItem1 = new MenuItem(1, "Mi cay", "7 cap do", 49_000, Origin.VIETNAMESE, 1);
        MenuItem menuItem2 = new MenuItem(2, "Com tron", "Rong bien", 30_000, Origin.VIETNAMESE, 1);
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);

        MockitoAnnotations.openMocks(this);

        menuItemRepository = new MenuItemRepositoryImpl(datasource);
        menuItemRepository.setMenuItems(menuItems);

        when(datasource.readData(MenuItem.class)).thenReturn(menuItemRepository.getMenuItems());

    }

    @AfterEach
    void tearDown() {
        menuItems.clear();
    }

    @Test
    @DisplayName("Test get all Menu Items")
    void getAll() {
        List<MenuItem> expectedMenuItems = menuItemRepository.getAll();

        assertEquals(menuItems.size(), expectedMenuItems.size());
        for (MenuItem expectedItem : expectedMenuItems) {
            assertTrue(menuItems.contains(expectedItem));
        }

        verify(datasource, times(2)).readData(MenuItem.class);
    }

    @Test
    @DisplayName("Test get List of Menu Item with menuId = 1")
    void getMenuItemsByMenuId() {
        List<MenuItem> actualMenuItems = menuItemRepository.getByMenuId(1);

        assertEquals(2, actualMenuItems.size());
        for(MenuItem item : actualMenuItems) {
            assertNotNull(item);
        }
    }

    @Test
    @DisplayName("Test get Menu Item with nonexistent menuId = 999")
    void getMenuItemsByNonexistentMenuId() {
        assertTrue(menuItemRepository.getByMenuId(999).isEmpty());
    }

    @Test
    @DisplayName("Test get Menu Item with id = 1")
    void getById() {
        MenuItem actualItem = menuItems.get(0);
        MenuItem expectedItem = menuItemRepository.getById(1).orElse(null);

        assertNotNull(expectedItem);
        assertEquals(expectedItem, actualItem);
    }

    @Test
    @DisplayName("Test get nonexistent Menu Item with id = 999")
    void getByNonexistentId() {
        Optional<MenuItem> actualItem = menuItemRepository.getById(999);

        assertTrue(actualItem.isEmpty());
    }

    @Test
    @DisplayName("Test create new menu")
    void createNewItem() {
        MenuItem expectedItem = new MenuItem(3, "Hu tieu xao", "chua ngot", 23_000, Origin.VIETNAMESE, 1);
        MenuItem actualItem = menuItemRepository.create(expectedItem).get();

        assertTrue(actualItem.getId() > 0);
        assertEquals(actualItem, expectedItem);

        verify(datasource, times(1)).saveAll(menuItemRepository.getMenuItems(), MenuItem.class);
    }

    @Test
    @DisplayName("Test update nonexistent menu with id = 999")
    void updateNonexistentMenu() {
        MenuItem item = new MenuItem(999, "Hu tieu xao", "chua ngot", 23_000, Origin.VIETNAMESE, 1);
        assertTrue(menuItemRepository.update(item).isEmpty());

        verify(datasource, times(0)).saveAll(menuItemRepository.getMenuItems(), MenuItem.class);
    }

    @Test
    @DisplayName("Test update Menu Item")
    void updateMenu() {
        MenuItem item = new MenuItem(menuItems.get(0));
        item.setName("Mi cay 7 cap do");
        item.setDescription("Rat ngon");

        MenuItem actualItem = menuItemRepository.update(item).get();

        assertEquals(actualItem, item);

        verify(datasource, times(1)).saveAll(menuItemRepository.getMenuItems(), MenuItem.class);
    }

    @Test
    @DisplayName("Test delete nonexistent Menu Item")
    void deleteNonexistentMenuItemById() {
        assertTrue(menuItemRepository.getById(999).isEmpty());
        menuItemRepository.deleteById(999);
        assertTrue(menuItemRepository.getById(999).isEmpty());
    }

    @Test
    @DisplayName("Test delete Menu Item with id = 1")
    void deleteMenuItemById() {
        List<MenuItem> menuItems = menuItemRepository.getAll();
        for (MenuItem item : menuItems) {
            assertFalse(item.isDeleted());
        }

        menuItemRepository.deleteById(1);

        menuItems = menuItemRepository.getAll();
        for (MenuItem item : menuItems) {
            if(item.getId() == 1) {
                assertTrue(item.isDeleted());
            } else {
                assertFalse(item.isDeleted());
            }
        }


        verify(datasource, times(1)).saveAll(menuItemRepository.getMenuItems(), MenuItem.class);
    }

    @Test
    @DisplayName("Test delete all Menu Item with menuId = 1")
    void deleteAllMenuItemsByMenuId() {
        List<MenuItem> menuItems = menuItemRepository.getAll();
        for (MenuItem item : menuItems) {
            assertFalse(item.isDeleted());
        }

        menuItemRepository.deleteAllMenuItemsByMenuId(1);

        menuItems = menuItemRepository.getAll();
        for (MenuItem item : menuItems) {
            if(item.getMenuId() == 1) {
                assertTrue(item.isDeleted());
            } else {
                assertFalse(item.isDeleted());
            }
        }

        verify(datasource, times(1)).saveAll(menuItemRepository.getMenuItems(), MenuItem.class);
    }

    @Test
    @DisplayName("Test delete all Menu Item with nonexistent menuId = 999")
    void deleteAllMenuItemsWithNonexistentMenuId() {
        menuItemRepository.deleteAllMenuItemsByMenuId(999);
        List<MenuItem> menuItems = menuItemRepository.getAll();
        for (MenuItem item : menuItems) {
            assertFalse(item.isDeleted());
        }
    }
}