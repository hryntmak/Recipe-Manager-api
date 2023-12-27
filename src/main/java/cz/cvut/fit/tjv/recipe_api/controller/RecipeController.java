package cz.cvut.fit.tjv.recipe_api.controller;

import cz.cvut.fit.tjv.recipe_api.domain.Recipe;
import cz.cvut.fit.tjv.recipe_api.service.RecipeService;
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
@RequestMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {
    private RecipeService recipeService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @RequestBody Recipe data) {
        recipeService.update(id, data);
    }

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(description = "Creat new recipe")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "duplicate ID", content = @Content)
    })
    public Recipe create(@RequestBody Recipe data) {
        try {
            return recipeService.create(data);
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{idRecipe}/ingredients/{idIngredient}")
    @Operation(description = "Add new ingredient to the recipe")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "invalid ID", content = @Content)
    })
    public void addIngredient(@PathVariable long idRecipe, @PathVariable long idIngredient) {
        try {
            recipeService.addIngredient(idRecipe, idIngredient);
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public Iterable<Recipe> readAllOrByName(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return recipeService.readAllByName(name.get());
        }
        else
            return recipeService.readAll();
    }

    @GetMapping("/ingredients/{price}")
    public Iterable<Recipe> readCheaperThen(@PathVariable double price) {
        return recipeService.readCheaperThan(price);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Can't delete. Recipe with this ID is not found.", content = @Content)
    })
    public void delete(@PathVariable long id) {
        if (recipeService.readById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteById(id);
    }
}
