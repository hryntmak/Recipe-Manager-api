package cz.cvut.fit.tjv.recipe_api.controller;

import cz.cvut.fit.tjv.recipe_api.domain.Ingredient;
import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.service.IngredientService;
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
@RequestMapping(value = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {
    private IngredientService ingredientService;

    @PutMapping("/{id}")
    @Operation(description = "Update new ingredient")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @RequestBody Ingredient data) {
        ingredientService.update(id, data);
    }

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(description = "Create new ingredient")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "duplicate ID", content = @Content)
    })
    public Ingredient create(@RequestBody Ingredient data) {
        try {
            return ingredientService.create(data);
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    @Operation(description = "Read all ingredients")
    public Iterable<Ingredient> readAll() {
        return ingredientService.readAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Read ingredient by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Recipe with this ID is not found.", content = @Content)
    })
    public Ingredient readIngredientById(@PathVariable long id) {
        if (ingredientService.readById(id).isPresent())
            return ingredientService.readById(id).get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    @Operation(description = "Delete ingredient by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Can't delete. Ingredient with this ID is not found.", content = @Content)
    })
    public void delete(@PathVariable long id) {
        if (ingredientService.readById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ingredientService.deleteById(id);
    }
}
