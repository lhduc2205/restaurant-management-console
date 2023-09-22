package com.lhduc.models.mappers;

import com.lhduc.common.enums.MenuCategory;
import com.lhduc.models.dtos.MenuDto;
import com.lhduc.models.entities.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapperTest {
    private static ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("Test map DTO to Entity")
    void testMapDtoToEntity() {
        MenuDto menuDto = new MenuDto(1, MenuCategory.DRINK);
        Menu menu = modelMapper.map(menuDto, Menu.class);

        assertNotNull(menu);

        assertEquals(menuDto.getId(), menu.getId());
        assertEquals(menuDto.getCategory(), menu.getCategory());
    }
    @Test
    @DisplayName("Test map null to Class")
    void testMapNullToClass() {
        assertThrows(IllegalArgumentException.class, () -> modelMapper.map(null, Menu.class));
    }

    @Test
    @DisplayName("Test map Optional.empty() to Class")
    void testMapOptionalWithNullValueToEntity() {
        assertThrows(NoSuchElementException.class, () -> modelMapper.map(Optional.empty(), Menu.class));
    }

    @Test
    @DisplayName("Test map Entity to Dto")
    void testMapEntityToDto() {
        Menu menu = new Menu(1, MenuCategory.FOOD);
        MenuDto menuDto = modelMapper.map(menu, MenuDto.class);

        assertNotNull(menuDto);

        assertEquals(menuDto.getId(), menu.getId());
        assertEquals(menuDto.getCategory(), menu.getCategory());
    }

    @Test
    @DisplayName("Test map Optional<Entity> to Dto")
    void testMapOptionalEntityToDto() {
        Menu menu = new Menu(1, MenuCategory.FOOD);
        MenuDto menuDto = modelMapper.map(Optional.of(menu), MenuDto.class);

        assertNotNull(menuDto);

        assertEquals(menuDto.getId(), menu.getId());
        assertEquals(menuDto.getCategory(), menu.getCategory());
    }

    @Test
    @DisplayName("Test map List<Entity> to List<Dto>")
    void testMapListEntityToListDto() {
        List<Menu> menus = new ArrayList<>(Arrays.asList(
                new Menu(1, MenuCategory.FOOD),
                new Menu(2, MenuCategory.DRINK)
        ));

        List<MenuDto> menusDto = modelMapper.mapList(menus, MenuDto.class);

        assertNotNull(menusDto);

        for (int i = 0; i < menusDto.size(); i++) {
            Menu menu = menus.get(i);
            MenuDto menuDto = menusDto.get(i);

            assertEquals(menu.getId(), menuDto.getId());
            assertEquals(menu.getCategory(), menuDto.getCategory());
        }
    }

    @Test
    @DisplayName("Test map List<Dto> to List<Entity>")
    void testMapListDtoToListEntity() {
        List<MenuDto> menusDto = new ArrayList<>(Arrays.asList(
                new MenuDto(1, MenuCategory.FOOD),
                new MenuDto(2, MenuCategory.DRINK)
        ));

        List<Menu> menus = modelMapper.mapList(menusDto, Menu.class);

        assertNotNull(menus);

        for (int i = 0; i < menus.size(); i++) {
            Menu menu = menus.get(i);
            MenuDto menuDto = menusDto.get(i);

            assertEquals(menu.getId(), menuDto.getId());
            assertEquals(menu.getCategory(), menuDto.getCategory());
        }
    }

    @Test
    @DisplayName("Test map List<Optional<Dto>> to List<Entity>")
    void testMapListOptionalDtoToListEntity() {
        List<Optional<MenuDto>> menusDto = new ArrayList<>(Arrays.asList(
                Optional.of(new MenuDto(1, MenuCategory.FOOD)),
                Optional.of(new MenuDto(2, MenuCategory.DRINK))
        ));

        List<Menu> menus = modelMapper.mapList(menusDto, Menu.class);

        assertNotNull(menus);

        for (int i = 0; i < menus.size(); i++) {
            Menu menu = menus.get(i);
            Optional<MenuDto> menuDto = menusDto.get(i);

            assertEquals(menu.getId(), menuDto.get().getId());
            assertEquals(menu.getCategory(), menuDto.get().getCategory());
        }
    }
}