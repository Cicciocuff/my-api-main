package com.fra.myapi.services.impl;

import com.fra.myapi.dto.responses.FoodResponseDTO;
import com.fra.myapi.dto.requests.FoodRequestDTO;
import com.fra.myapi.entities.Food;
import com.fra.myapi.exceptions.FoodNotFoundException;
import com.fra.myapi.repositories.FoodRepository;
import com.fra.myapi.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    public FoodRepository repository;

    @Override
    public List<FoodResponseDTO> findAll() {
        List<Food> list = repository.findAll();
        return list.stream().map(FoodResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public FoodResponseDTO findById(Long id) throws FoodNotFoundException {
        return new FoodResponseDTO(repository.findById(id).orElseThrow(() -> new FoodNotFoundException(id)));
    }

    @Override
    public FoodResponseDTO insert(FoodRequestDTO dto) {
        Food foodToSave = repository.save(new Food(dto));
        return new FoodResponseDTO(foodToSave);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
