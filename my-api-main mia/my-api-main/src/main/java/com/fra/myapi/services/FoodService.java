package com.fra.myapi.services;

import com.fra.myapi.dto.responses.FoodResponseDTO;
import com.fra.myapi.dto.requests.FoodRequestDTO;
import java.util.List;

public abstract interface FoodService {

    public List<FoodResponseDTO> findAll();

    public FoodResponseDTO findById(Long id);

    public FoodResponseDTO insert(FoodRequestDTO dto);

     public void delete(Long id);
}




