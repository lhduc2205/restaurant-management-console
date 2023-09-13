package models.mappers;

import common.enums.MenuCategory;
import models.dtos.MenuDto;
import models.entities.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ModelMapperTest {

    private static ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("Test map object to object")
    void testMap() {
        MenuDto menuDto = new MenuDto(MenuCategory.DRINK);
        Menu menu = modelMapper.map(menuDto, Menu.class);

        Assertions.assertEquals(menuDto.getCategory(), menu.getCategory());
    }
}