package com.fra.myapi.dto.responses;


import com.fra.myapi.entities.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodResponseDTO {

    private Long id;
    private String name;
    private String email;

    public FoodResponseDTO(Food food) {
        id = food.getId();
        name = food.getName();
        email = food.getEmail();
    }
}
