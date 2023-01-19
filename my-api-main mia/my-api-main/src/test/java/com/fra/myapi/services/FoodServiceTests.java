package com.fra.myapi.services;

import com.fra.myapi.dto.responses.FoodResponseDTO;
import com.fra.myapi.entities.Food;
import com.fra.myapi.exceptions.FoodNotFoundException;
import com.fra.myapi.repositories.FoodRepository;
import com.fra.myapi.services.impl.FoodServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodServiceTests {

    private final FoodServiceImpl service = new FoodServiceImpl();
    private final FoodRepository repository = Mockito.mock(FoodRepository.class);
    private final Food templateFood = new Food(1L, "Fra", "fcuffaro97@gmail.com");

    @BeforeEach
    void setUp() {
        service.repository = repository;
        var list = new ArrayList<Food>(List.of(templateFood));
        Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(templateFood));
        Mockito.when(repository.findAll()).thenReturn(list);
    }

    private void assertFoodsAreEqual(FoodResponseDTO actual, FoodResponseDTO expected) {
        assertAll(() -> actual.getEmail().equals(expected.getEmail()),
                () -> actual.getName().equals(expected.getName()),
                () -> actual.getId().equals(expected.getId()));
    }

    @Test
    @DisplayName("Should return valid food dto when id is valid")
    void shouldReturnValidFoodWhenIdIsValid() {
        var actual = service.findById(1L);
        var expected = new FoodResponseDTO(new Food(1L, "fra", "fcuffaro97@gmail.com"));
        assertFoodsAreEqual(actual, expected);
    }

    @Test
    @DisplayName("Should throw FoodNotFoundException when given invalid id")
    void shouldThrowFoodNotFoundExceptionWhenGivenInvalidId() {
        assertThrows(FoodNotFoundException.class, () -> service.findById(10L));
    }

    @Test
    @DisplayName("Should return list of foods")
    void shouldReturnListOfFoods() {
        var actual = service.findAll();
        Assertions.assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.get(0).getName(), templateFood.getName()),
                () -> assertEquals(actual.get(0).getEmail(), templateFood.getEmail()),
                () -> assertEquals(actual.get(0).getId(), templateFood.getId()));
    }

    @Test
    @DisplayName("Should return empty list when database is empty")
    void ShouldReturnEmptyListWhenDatabaseIsEmpty() {
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<Food>());
        var expected = service.findAll();
        assertTrue(expected.isEmpty());
    }

    // TODO: Implement return, mock repository to return registry with id
    @Test
    @Disabled
    @DisplayName("Should insert new food and return FoodResponse when valid args")
    void ShouldInsertNewFoodAndReturnFoodResponseWhenValidArgs() {
        assertTrue(true);
    }
}
