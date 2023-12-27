package cz.cvut.fit.tjv.recipe_api.controller;

import cz.cvut.fit.tjv.recipe_api.domain.Dish;
import cz.cvut.fit.tjv.recipe_api.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @RequestBody Dish data) {
        dishService.update(id, data);
    }

    @PostMapping
    @Operation(description = "create new dish")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "duplicate ID", content = @Content)
    })
    public Dish create(@RequestBody Dish data) {
        try {
            return dishService.create(data);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public Iterable<Dish> readAllOrByName(@RequestParam Optional<String> name) {
        return dishService.readAll();
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Can't delete. Ingredient with this ID is not found.", content = @Content)
    })
    public void delete(@PathVariable long id) {
        if (dishService.readById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        dishService.deleteById(id);
    }
}
