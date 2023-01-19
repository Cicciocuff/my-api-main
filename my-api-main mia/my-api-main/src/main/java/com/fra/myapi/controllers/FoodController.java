package com.fra.myapi.controllers;

import com.fra.myapi.dto.responses.FoodResponseDTO;
import com.fra.myapi.dto.requests.FoodRequestDTO;
import com.fra.myapi.services.FoodService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@Api(tags = "Foods")
@RequestMapping(value = "/foods", produces = "application/json")
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }

    @ApiOperation(value = "Returns all foods in database.")
    @GetMapping
    @ApiResponse(code = 200, message = "OK.")
    public ResponseEntity<List<FoodResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Returns food by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Food not found.")
    })
    public ResponseEntity<FoodResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Create a new food in database.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Food created with success."),
            @ApiResponse(code = 400, message = "Problem with request.")
    })
    public ResponseEntity<FoodResponseDTO> insert(@RequestBody FoodRequestDTO food) {
        var createdFood = service.insert(food);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(createdFood.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(createdFood);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete food by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted with success."),
            @ApiResponse(code = 404, message = "Food not found."),
            @ApiResponse(code = 400, message = "Problem with request.")
    })
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
